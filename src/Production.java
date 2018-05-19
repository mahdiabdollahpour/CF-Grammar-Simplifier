import java.util.ArrayList;

/**
 * Created by ASUS on 19/05/2018.
 */

public class Production {
    Symbol leftside;
    ArrayList<Symbol> rightsides;

    public Production(Symbol left, ArrayList<Symbol> rightsides) {
        this.leftside = left;
        this.rightsides = rightsides;
    }

    public Production(String left, String right, String rightPattern) {

        leftside = new Variable(left);

        rightsides = new ArrayList<>();
        for (int j = 0; j < right.length(); j++) {
            char sym = right.charAt(j);
            if (rightPattern.charAt(j) == 'T') {
                rightsides.add(new Terminal(Character.toString(sym)));
            } else if (rightPattern.charAt(j) == 'V') {

                rightsides.add(new Variable(Character.toString(sym)));
            } else {
                rightsides.add(new Lambda());

            }
        }

    }


    public String toString() {
        String s = "";
        s = s.concat(leftside.toString());
        s = s.concat(" --> ");
        for (int i1 = 0; i1 < rightsides.size(); i1++) {
            s = s.concat(rightsides.get(i1).toString());
        }

        return s;
    }
}

