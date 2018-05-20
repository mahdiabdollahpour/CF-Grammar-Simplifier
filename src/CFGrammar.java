import java.util.ArrayList;

/**
 * Created by ASUS on 19/05/2018.
 */
public class CFGrammar {
    public ArrayList<Variable> getVariable() {
        return variable;
    }

    public ArrayList<Terminal> getTerminal() {
        return terminal;
    }

    public ArrayList<Production> getProductions() {
        return productions;
    }

    public int getStart() {
        return start;
    }

    public CFGrammar(ArrayList<Variable> variable, ArrayList<Terminal> terminal, int start, ArrayList<Production> productions) {
        this.variable = variable;
        this.terminal = terminal;
        this.productions = productions;
        this.start = start;
    }

    public CFGrammar(String variables, String terminals, int start, ArrayList<Production> production) {

        variable = new ArrayList<>();
        terminal = new ArrayList<>();
        productions = new ArrayList<>();
        String[] vars = variables.split(",");
        String[] terms = terminals.split(",");
        for (int i = 0; i < vars.length; i++) {
            variable.add(new Variable(vars[i]));
        }
        for (int i = 0; i < terms.length; i++) {
            terminal.add(new Terminal(terms[i]));
        }


        this.start = start;
        this.productions = production;
    }

    private ArrayList<Variable> variable;
    private ArrayList<Terminal> terminal;
    private ArrayList<Production> productions;




    int start;


    public String toString() {
        String s = "Variables : " + variable.toString() + ", Terminals : " + terminal.toString() + ", start : " + variable.get(start) + "\n" + "Productions : ";
        for (int i = 0; i < productions.size(); i++) {
            //   System.out.println("hi");
            s = s + "\n" + productions.get(i).toString();
        }
        return s;
    }
}
