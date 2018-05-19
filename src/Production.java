import java.util.ArrayList;

/**
 * Created by ASUS on 19/05/2018.
 */

public class Production {
    ArrayList<Symbol> leftside;
    ArrayList<ArrayList<Symbol>> rightsides;

    public Production(String left, String pattern, String[] right, String[] rightPattern) {
        leftside = new ArrayList<>();
        for (int i = 0; i < left.length(); i++) {
            char sym = left.charAt(i);
            if (pattern.charAt(i) == 'T') {
                leftside.add(new Terminal(sym));
            } else if (pattern.charAt(i) == 'V') {
                leftside.add(new Variable(sym));

            } else {
                leftside.add(new Lambda());

            }
        }
        rightsides = new ArrayList<>();
        for (int i = 0; i < right.length; i++) {
            String s = right[i];
            ArrayList<Symbol> rs = new ArrayList<>();
            for (int j = 0; j < s.length(); j++) {
                char sym = s.charAt(j);
                if (rightPattern[i].charAt(j) == 'T') {
                    rs.add(new Terminal(sym));
                } else if (rightPattern[i].charAt(j) == 'V') {

                    rs.add(new Variable(sym));
                } else {
                    rs.add(new Lambda());

                }
            }
            rightsides.add(rs);
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < leftside.size(); i++) {
            s = s.concat(leftside.get(i).toString());
        }
        s = s.concat(" --> ");
        for (int i = 0; i < rightsides.size(); i++) {
            ArrayList<Symbol> rs = rightsides.get(i);
            for (int i1 = 0; i1 < rs.size(); i1++) {
                s = s.concat(rs.get(i1).toString());
            }
            if (i != rightsides.size() - 1) {
                s = s.concat(" | ");
            }
        }
        return s;
    }
}
