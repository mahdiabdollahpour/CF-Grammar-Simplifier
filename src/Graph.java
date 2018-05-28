import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ASUS on 22/05/2018.
 */

public class Graph {
    ArrayList<Integer>[] list = new ArrayList[26];

    public Graph() {
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
    }

    public void FromProductions(ArrayList<Production> productions) {
        for (int i = 0; i < productions.size(); i++) {
            Production p = productions.get(i);
            int s = p.leftside.sign - 65;
            for (int j = 0; j < p.rightsides.size(); j++) {
                if (p.rightsides.get(j) instanceof Variable) {
                    int d = p.rightsides.get(j).sign - 65;
                    list[s].add(d);
                  //  System.out.println(s + " has arc to " + d);
                }
            }
        }

    }

    public boolean isPath(Symbol s1, Symbol d1) {
        Queue<Integer> q = new LinkedList<>();
        int s = s1.sign - 65;
        int d = d1.sign - 65;
        q.add(s);

//        System.out.println(list);
//        System.out.println(s + " ---- " + d);
        boolean[] mark = new boolean[list.length];


        mark[s] = true;
        while (!q.isEmpty()) {
            //    System.out.println("in bfs");
            int now = q.poll();
            if (now == d) {
                return true;
            }
            for (int i = 0; i < list[now].size(); i++) {
                int toAdd = list[now].get(i);
                if (!mark[toAdd]) {

                    q.add(toAdd);
                    mark[toAdd] = true;
                }

            }
        }
        return false;

    }


}
