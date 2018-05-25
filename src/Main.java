import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ASUS on 19/05/2018.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        ArrayList<Production> productions = new ArrayList<>();
        ArrayList<Variable> variables = new ArrayList<>();
        ArrayList<Terminal> terminals = new ArrayList<>();
        while (!s.equals("-1")) {
       //     System.out.println("hi");
            productions.add(new Production(s.charAt(0), s.substring(1, s.length())));
            for (int i = 0; i < s.length(); i++) {
                if(Character.isLowerCase(s.charAt(i)) && s.charAt(i)!='l'){
                    Terminal t = new Terminal(s.charAt(i));
                    if(!terminals.contains(t)){
                        terminals.add(t);
                    }
                }else if(!Character.isLowerCase(s.charAt(i)) && s.charAt(i)!='l'){
                    Variable v = new Variable(s.charAt(i));
                    if(!variables.contains(v)){
                        variables.add(v);
                    }
                }
            }
            s = in.next();
        }

        CFGrammar g = new CFGrammar(variables,terminals,0,productions);
        System.out.println("the grammar :");
        System.out.println(g);
        System.out.println("-----------------------");
        System.out.println("simplified :");
        System.out.println(Simplifier.simplify(g));
     //   System.out.println(Simplifier.removeUseless(g));



    }
}
