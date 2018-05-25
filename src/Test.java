import java.util.ArrayList;

/**
 * Created by ASUS on 19/05/2018.
 */
public class Test {
    public static void main(String[] args) {

        test3();
        //   test2();
        //       test1();
    }

    static void test2() {
        Production p = new Production('S', "ABaC");
        Production p2 = new Production('A', "BC");
        Production p3 = new Production('B', "b");
        Production p4 = new Production('B', "!");

        Production p5 = new Production('C', "D");
        Production p6 = new Production('C', "L");
        Production p7 = new Production('D', "d");
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
        Production p = new Production('S', "aS");
        Production p5 = new Production('S', "C");
        Production p6 = new Production('S', "A");
        Production p2 = new Production('A', "a");
        Production p3 = new Production('B', "aa");
        Production p4 = new Production('C', "aCb");
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

    static void test3() {
        Production p = new Production('S', "Aa");
        Production p2 = new Production('S', "B");
        Production p3 = new Production('B', "A");
        Production p4 = new Production('B', "bb");
        Production p5 = new Production('A', "a");
        Production p6 = new Production('A', "bc");
        Production p7 = new Production('A', "B");
        ArrayList<Production> productions = new ArrayList<>();
        productions.add(p);
        productions.add(p2);
        productions.add(p3);
        productions.add(p4);
        productions.add(p5);
        productions.add(p6);
        productions.add(p7);
        CFGrammar g = new CFGrammar("S,A,B", "a,b,c", 0, productions);
        System.out.println(g);
        System.out.println(Simplifier.removeUnit(g));


    }
}
