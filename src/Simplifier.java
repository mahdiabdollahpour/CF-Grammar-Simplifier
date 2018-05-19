import java.util.ArrayList;

/**
 * Created by ASUS on 19/05/2018.
 */

public class Simplifier {

    public static CFGrammar removeUseless(CFGrammar g) {
        ArrayList<Variable> v1 = new ArrayList<>();


        boolean wasAdd = true;

        while (wasAdd) {
            //  System.out.println("cnt");
            wasAdd = false;
            for (int idx = 0; idx < g.getProductions().size(); idx++) {
                Production p = g.getProductions().get(idx);
                //   System.out.println(p);
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
        //   System.out.println(v1);
        ArrayList<Variable> v2 = new ArrayList<>();
        ArrayList<Production> gproductions = g.getProductions();
        for (int i = 0; i < v1.size(); i++) {
            Variable symbol = v1.get(i);
            for (int i1 = 0; i1 < gproductions.size(); i1++) {
                Production p = gproductions.get(i1);
                if (p.rightsides.contains(symbol)) {
                    if (!v2.contains(symbol)) {
                        v2.add(symbol);
                    }
                }
            }
        }
        v1 = v2;
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
        //   System.out.println(productions);
        //  System.out.println(g.getTerminal());
        return new CFGrammar(v1, g.getTerminal(), v1.indexOf(g.getVariable().get(g.getStart())), productions);

    }

}
