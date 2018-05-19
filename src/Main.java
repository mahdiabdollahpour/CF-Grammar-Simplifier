import java.util.ArrayList;

/**
 * Created by ASUS on 19/05/2018.
 */
public class Main {
    public static void main(String[] args) {

        Production p = new Production("A", "V", new String[]{"aA", "!"}, new String[]{"TV", "L"});
        ArrayList<Production> productions = new ArrayList<>();
        productions.add(p);
        Grammar g = new Grammar("A","a", 0,productions );
        System.out.println(g.toString());

    }
}
