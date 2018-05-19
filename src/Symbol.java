/**
 * Created by ASUS on 19/05/2018.
 */
public abstract class Symbol {

    public String sign;

    public String toString() {
        return sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol = (Symbol) o;

        return sign.equals(symbol.sign);
    }


}
