import java.util.ArrayList;

/**
 * Created by ASUS on 19/05/2018.
 */

public class Simplifier {

    public static CFGrammar removeUseless(CFGrammar g) {
        ArrayList<Variable> v1 = new ArrayList<>();


        boolean wasAdd = true;

        while (wasAdd) {

            wasAdd = false;
            for (int idx = 0; idx < g.getProductions().size(); idx++) {
                Production p = g.getProductions().get(idx);

                ArrayList<Symbol> right = p.rightsides;
                boolean allOk = true;
                for (int i = 0; i < right.size(); i++) {
                    Symbol symbol = right.get(i);
                    if (symbol instanceof Variable) {
                        if (!v1.contains(symbol)) {
                            allOk = false;
                            break;
                        }
                    }
                }
                if (allOk) {
                    //   System.out.println("hi");
                    if (!v1.contains(p.leftside)) {
                        v1.add((Variable) p.leftside);
                        wasAdd = true;
                    }
                }
            }


        }

        ArrayList<Variable> reachable = new ArrayList<>();
        reachable.add(g.getVariable().get(g.getStart()));
        ArrayList<Production> gproductions = g.getProductions();
        for (int i = 0; i < v1.size(); i++) {
            Variable symbol = v1.get(i);
            for (int i1 = 0; i1 < gproductions.size(); i1++) {
                Production p = gproductions.get(i1);
                if (reachable.contains(p.leftside)) {
                    if (p.rightsides.contains(symbol)) {
                        if (!reachable.contains(symbol)) {
                            reachable.add(symbol);
                        }
                    }
                }
            }
        }
        v1 = reachable;
        ArrayList<Production> productions = new ArrayList<>();
        for (int i = 0; i < gproductions.size(); i++) {
            Production p = gproductions.get(i);
            boolean canAdd = true;

            if (v1.contains(p.leftside)) {
                ArrayList<Symbol> right = p.rightsides;
                for (int j = 0; j < right.size(); j++) {
                    if (right.get(j) instanceof Variable && !v1.contains(right.get(j))) {
                        canAdd = false;
                        break;
                    }
                }
            } else {
                canAdd = false;
            }
            if (canAdd) {
                productions.add(p);
            }
        }
        return new CFGrammar(v1, g.getTerminal(), v1.indexOf(g.getVariable().get(g.getStart())), productions);

    }

    public static CFGrammar removeLambdaProduction(CFGrammar g) {
        ArrayList<Variable> vn = new ArrayList<>();

        ArrayList<Production> productions = g.getProductions();
        for (int i = 0; i < productions.size(); i++) {
            Production p = productions.get(i);
            if (p.rightsides.size() == 1) {
                if (p.rightsides.get(0) instanceof Lambda) {
                    vn.add(p.leftside);
                }
            }
        }


        boolean wasAdded = true;
        while (wasAdded) {
            wasAdded = false;
            for (int i = 0; i < productions.size(); i++) {
                Production p = productions.get(i);
                boolean shouldAdd = true;
                for (int i1 = 0; i1 < p.rightsides.size(); i1++) {
                    Symbol symbol = p.rightsides.get(i1);
                    if (!(symbol instanceof Variable) && !vn.contains(symbol)) {
                        shouldAdd = false;
                        break;
                    }
                }
                if (shouldAdd) {
                    if (!vn.contains(p.leftside)) {
                        vn.add(p.leftside);
                        wasAdded = true;
                    }
                }

            }


        }
        ArrayList<Production> newProductions = new ArrayList<>();
        for (int i = 0; i < productions.size(); i++) {

            ArrayList<Production> productions1 = generateProductionsDriver(vn, productions.get(i));
            for (Production production : productions1) {
                newProductions.add(production);
            }
        }
        return new CFGrammar(g.getVariable(), g.getTerminal(), g.getStart(), newProductions);

    }

    private static ArrayList<ArrayList<Symbol>> rightSides = null;

    private static ArrayList<Production> generateProductionsDriver(final ArrayList<Variable> vn, final Production p) {
        rightSides = new ArrayList<>();
        generateProductionsWorkHorse(vn, p, new ArrayList<>(), 0);
        ArrayList<Production> productions = new ArrayList<>();
        for (int i = 0; i < rightSides.size(); i++) {
            productions.add(new Production(p.leftside, rightSides.get(i)));
        }
        return productions;
    }

    private static void generateProductionsWorkHorse(final ArrayList<Variable> vn, final Production p, ArrayList<Symbol> tillnow, int now) {
        if (now < p.rightsides.size()) {
            Symbol symbol = p.rightsides.get(now);
            if (symbol instanceof Terminal) {
                tillnow.add(symbol);
                generateProductionsWorkHorse(vn, p, tillnow, now + 1);
            } else if (symbol instanceof Variable) {
                if (vn.contains(symbol)) {
                    ArrayList<Symbol> arr1 = (ArrayList<Symbol>) tillnow.clone();

                    generateProductionsWorkHorse(vn, p, arr1, now + 1);
                }
                tillnow.add(symbol);
                generateProductionsWorkHorse(vn, p, tillnow, now + 1);
            }
            // we add nothing for Lambdas
        } else { // now = len
            if (tillnow.size() != 0) {
                rightSides.add(tillnow);
            }
        }


    }

    public static CFGrammar removeUnit(CFGrammar g) {
        ArrayList<Production> productions = g.getProductions();
        ArrayList<Production> newProductions = new ArrayList<>();
        ArrayList<Production> unitProductions = new ArrayList<>();
        ArrayList<Variable> unitVars = new ArrayList<>();
        for (int i = 0; i < productions.size(); i++) {
            Production p = productions.get(i);
            if (p.rightsides.size() != 1) {
                newProductions.add(p);
            } else {
                if (p.rightsides.get(0) instanceof Terminal) {
                    newProductions.add(p);

                } else {
                    unitProductions.add(p);
                    unitVars.add(p.leftside);
                }
            }
        }
     //   System.out.println(unitProductions);
        ArrayList<ArrayList<Variable>> AgoesTo = new ArrayList<>();


        Graph graph = new Graph();
        graph.FromProductions(unitProductions);

        for (int i = 0; i < unitVars.size(); i++) {
            Symbol symbol = unitVars.get(i);
            AgoesTo.add(new ArrayList<>());
            for (int i1 = 0; i1 < unitProductions.size(); i1++) {
                Production p = unitProductions.get(i1);
                if (graph.isPath(symbol, p.leftside)) {

                    AgoesTo.get(i).add(p.leftside);
                }


            }
        }


        ArrayList<Production> created = new ArrayList<>();
        for (int i = 0; i < unitVars.size(); i++) {
            ArrayList<Variable> vars = AgoesTo.get(i);
            for (int i1 = 0; i1 < vars.size(); i1++) {
                Variable v = vars.get(i1);
                for (int i2 = 0; i2 < newProductions.size(); i2++) {
                    Production p = newProductions.get(i2);
                    if (p.leftside.equals(v)) {
                        Production pr = new Production(unitVars.get(i), p.rightsides);
                        if (!created.contains(pr))
                            created.add(pr);
                    }
                }
            }
        }

        for (int i = 0; i < created.size(); i++) {
            if (!newProductions.contains(created.get(i))) {
                newProductions.add(created.get(i));
            }
        }


        return new CFGrammar(g.getVariable(), g.getTerminal(), g.getStart(), newProductions);

    }

}
