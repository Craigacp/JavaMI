package JavaMI;

/**
 *
 * @author craigacp
 */
public class Pair<T,U> {
	public final T a;
    public final U b;

    public Pair(T a, U b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash ^ a.hashCode() ^ b.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        if (this.a != other.a && (this.a == null || !this.a.equals(other.a))) {
            return false;
        }
        if (this.b != other.b && (this.b == null || !this.b.equals(other.b))) {
            return false;
        }
        return true;
    }

}
