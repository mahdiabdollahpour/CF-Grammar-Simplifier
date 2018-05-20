import java.util.ArrayList;

/**
 * Created by ASUS on 19/05/2018.
 */
public class Test {
    public static void main(String[] args) {

        test2();
        //       test1();
    }

    static void test2() {
        Production p = new Production("S", "A,B,a,C", "VVTV");
        Production p2 = new Production("A", "B,C", "VV");
        Production p3 = new Production("B", "b", "T");
        Production p4 = new Production("B", "!", "L");

        Production p5 = new Production("C", "D", "V");
        Production p6 = new Production("C", "!", "L");
        Production p7 = new Production("D", "d", "T");
        ArrayList<Production> productions = new ArrayList<>();
        productions.add(p);
        productions.add(p2);
        productions.add(p3);
        productions.add(p4);
        productions.add(p5);
        productions.add(p6);
        productions.add(p7);
        CFGrammar g = new CFGrammar("S,A,B,C,D", "a,b,c,d", 0, productions);
        System.out.println(g);
        CFGrammar g2 = Simplifier.removeLambdaProduction(g);
        System.out.println(g2);


    }

    static void test1() {
        Production p = new Production("S", "a,S", "TV");
        Production p5 = new Production("S", "C", "V");
        Production p6 = new Production("S", "A", "V");
        Production p2 = new Production("A", "a", "T");
        Production p3 = new Production("B", "a,a", "TT");
        Production p4 = new Production("C", "a,C,b", "TVT");
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
