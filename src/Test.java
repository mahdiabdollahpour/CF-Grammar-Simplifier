import java.util.ArrayList;

/**
 * Created by ASUS on 19/05/2018.
 */
public class Test {
    public static void main(String[] args) {

        Production p = new Production("S", "aS", "TV");
        Production p5 = new Production("S", "C", "V");
        Production p6 = new Production("S", "A", "V");
        Production p2 = new Production("A", "a", "T");
        Production p3 = new Production("B", "aa", "TT");
        Production p4 = new Production("C", "aCb", "TVT");
        ArrayList<Production> productions = new ArrayList<>();
        productions.add(p);
        productions.add(p2);
        productions.add(p3);
        productions.add(p4);
        productions.add(p5);
        productions.add(p6);
        CFGrammar g = new CFGrammar("S,A,B,C", "a,b", 0, productions);
        System.out.println(Simplifier.removeUseless(g).toString());
    }
}
