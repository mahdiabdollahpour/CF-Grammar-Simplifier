import java.util.ArrayList;

/**
 * Created by ASUS on 19/05/2018.
 */

public class Production {
    Variable leftside;
    ArrayList<Symbol> rightsides;

    public Production(Variable left, ArrayList<Symbol> rightsides) {
        this.leftside = left;
        this.rightsides = rightsides;
    }

    public Production(String left, String right, String rightPattern) {

        leftside = new Variable(left);

        rightsides = new ArrayList<>();
        String[] r = right.split(",");
        for (int j = 0; j < r.length; j++) {
            String sym = r[j];
            if (rightPattern.charAt(j) == 'T') {
                rightsides.add(new Terminal(sym));
            } else if (rightPattern.charAt(j) == 'V') {

                rightsides.add(new Variable(sym));
            } else {
                rightsides.add(new Lambda());

            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Production that = (Production) o;

        if (leftside != null ? !leftside.equals(that.leftside) : that.leftside != null) return false;
        return rightsides != null ? rightsides.equals(that.rightsides) : that.rightsides == null;
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

