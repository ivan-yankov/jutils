package yankov.jutils.functional.tuples;

import java.util.Objects;

public class Tuple4<A, B, C, D> {
    private final A a;
    private final B b;
    private final C c;
    private final D d;

    public Tuple4(A a, B b, C c, D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
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

    public D _4() {
        return d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple4<?, ?, ?, ?> tuple4 = (Tuple4<?, ?, ?, ?>) o;
        return a.equals(tuple4.a) && b.equals(tuple4.b) && c.equals(tuple4.c) && d.equals(tuple4.d);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }
}
