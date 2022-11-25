package yankov.jutils.functional;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Either<L, R> {
    private final L left;
    private final R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public Optional<L> getLeft() {
        return left != null ? Optional.of(left) : Optional.empty();
    }

    public Optional<R> getRight() {
        return right != null ? Optional.of(right) : Optional.empty();
    }

    public <T> Either<L, T> map(Function<R, T> f) {
        if (getRight().isPresent()) {
            return right(f.apply(getRight().get()));
        } else if (getLeft().isPresent()) {
            return left(getLeft().get());
        } else {
            throw bothNullException();
        }
    }

    public void fold(Consumer<R> onRight, Consumer<L> onLeft) {
        if (getRight().isPresent()) {
            onRight.accept(getRight().get());
        } else if (getLeft().isPresent()) {
            onLeft.accept(getLeft().get());
        } else {
            throw bothNullException();
        }
    }

    private IllegalStateException bothNullException() {
        return new IllegalStateException("Both left and right of the Either are null.");
    }

    public static <L, R> Either<L, R> left(L value) {
        return new Either<>(value, null);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Either<>(null, value);
    }
}
