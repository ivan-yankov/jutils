package yankov.jutils.functional.tuples;

import java.util.Objects;

public class Tuple3<A, B, C> {
    private final A a;
    private final B b;
    private final C c;

    public Tuple3(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public A _1() {
        return a;
    }

    public B _2() {
        return b;
    }

    public C _3() {
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;
        return a.equals(tuple3.a) && b.equals(tuple3.b) && c.equals(tuple3.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
