import java.util.ArrayList;

/**
 * Created by ASUS on 19/05/2018.
 */
public class Grammar {
    public Grammar(String variables, String terminals, int start, ArrayList<Production> production) {

        variable = new ArrayList<>();
        terminal = new ArrayList<>();
        productions = new ArrayList<>();
        for (int i = 0; i < variables.length(); i++) {
            variable.add(new Variable(variables.charAt(i)));
        }
        for (int i = 0; i < variables.length(); i++) {
            terminal.add(new Terminal(terminals.charAt(i)));
        }


        this.start = start;
        this.productions = production;
    }

    private ArrayList<Variable> variable;
    private ArrayList<Terminal> terminal;
    private ArrayList<Production> productions;

    public Grammar() {
        variable = new ArrayList<>();
        terminal = new ArrayList<>();
        productions = new ArrayList<>();
    }


    int start;


    public String toString() {
        String s = "Variables : " + variable.toString() + ", Terminals : " + terminal.toString() + ", start : " + variable.get(start) + "\n" + "Productions : ";
        for (int i = 0; i < productions.size(); i++) {
            //   System.out.println("hi");
            s = s + "\n" + productions.get(i).toString() + "\n";
        }
        return s;
    }
}
