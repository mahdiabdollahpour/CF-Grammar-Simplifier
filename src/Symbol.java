/**
 * Created by ASUS on 19/05/2018.
 */
public abstract class Symbol {

    public char sign;

    public String toString() {
        return String.valueOf(sign);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol = (Symbol) o;
        if (this instanceof Lambda && o instanceof Lambda) {
            return true;
        }
        return sign == symbol.sign;
    }


}
