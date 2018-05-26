import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ASUS on 22/05/2018.
 */

public class Graph {
    ArrayList<ArrayList<Symbol>> list = new ArrayList<>();
    ArrayList<Symbol> indexes = new ArrayList<>();

    public void FromProductions(ArrayList<Production> productions) {
        for (int i = 0; i < productions.size(); i++) {
            Production p = productions.get(i);
            if (!indexes.contains(p.leftside)) {
                indexes.add(p.leftside);
                list.add(new ArrayList<>());
            }
            for (int j = 0; j < p.rightsides.size(); j++) {

                if (!indexes.contains(p.rightsides.get(j))) {
                    indexes.add(p.rightsides.get(j));
                    list.add(new ArrayList<>());
                }
                list.get(indexes.indexOf(p.leftside)).add(p.rightsides.get(j));
            }
        }
    }

    public boolean isPath(Symbol s, Symbol d) {
        Queue<Symbol> q = new LinkedList<>();
        q.add(s);
//        System.out.println(list);
//        System.out.println(s + " ---- " + d);
        boolean[] mark = new boolean[list.size()];
        if (indexes.indexOf(s) == -1) {
            return false;
        }
        if (indexes.indexOf(d) == -1) {
            return false;
        }

        mark[indexes.indexOf(s)] = true;
        while (!q.isEmpty()) {
            //    System.out.println("in bfs");
            Symbol now = q.poll();
            if (now.equals(d)) {
                return true;
            }
            for (int i = 0; i < list.get(indexes.indexOf(now)).size(); i++) {
                Symbol toAdd = list.get(indexes.indexOf(now)).get(i);
                if (!mark[indexes.indexOf(toAdd)]) {

                    q.add(toAdd);
                    mark[indexes.indexOf(toAdd)] = true;
                }

            }
        }
        return false;

    }


}
