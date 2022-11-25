package yankov.jutils.functional.tuples;

import java.util.Objects;

public class Tuple<A, B> {
    private final A a;
    private final B b;

    public Tuple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A _1() {
        return a;
    }

    public B _2() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) o;
        return a.equals(tuple.a) && b.equals(tuple.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
