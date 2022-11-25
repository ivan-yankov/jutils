package yankov.jutils.functional;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;

public class ExtendedStream<T> implements Stream<T> {
    private final Stream<T> stream;

    public ExtendedStream(Stream<T> stream) {
        this.stream = stream;
    }

    public static <A> ExtendedStream<A> concat(Stream<A> s1, Stream<A> s2) {
        return new ExtendedStream<>(Stream.concat(s1, s2));
    }

    public ImmutableList<T> toList() {
        return ImmutableList.of(stream.collect(Collectors.toList()));
    }

    @Override
    public ExtendedStream<T> filter(Predicate<? super T> predicate) {
        return new ExtendedStream<>(stream.filter(predicate));
    }

    @Override
    public <R> ExtendedStream<R> map(Function<? super T, ? extends R> mapper) {
        return new ExtendedStream<>(stream.map(mapper));
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        return stream.mapToInt(mapper);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
        return stream.mapToLong(mapper);
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
        return stream.mapToDouble(mapper);
    }

    @Override
    public <R> ExtendedStream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return new ExtendedStream<>(stream.flatMap(mapper));
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
        return stream.flatMapToInt(mapper);
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
        return stream.flatMapToLong(mapper);
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
        return stream.flatMapToDouble(mapper);
    }

    @Override
    public ExtendedStream<T> distinct() {
        return new ExtendedStream<>(stream.distinct());
    }

    @Override
    public ExtendedStream<T> sorted() {
        return new ExtendedStream<>(stream.sorted());
    }

    @Override
    public ExtendedStream<T> sorted(Comparator<? super T> comparator) {
        return new ExtendedStream<>(stream.sorted(comparator));
    }

    @Override
    public ExtendedStream<T> peek(Consumer<? super T> action) {
        return new ExtendedStream<>(stream.peek(action));
    }

    @Override
    public ExtendedStream<T> limit(long maxSize) {
        return new ExtendedStream<>(stream.limit(maxSize));
    }

    @Override
    public ExtendedStream<T> skip(long n) {
        return new ExtendedStream<>(stream.skip(n));
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        stream.forEach(action);
    }

    @Override
    public void forEachOrdered(Consumer<? super T> action) {
        stream.forEachOrdered(action);
    }

    @Override
    public Object[] toArray() {
        return stream.toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        return stream.toArray(generator);
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return stream.reduce(identity, accumulator);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return stream.reduce(accumulator);
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return stream.reduce(identity, accumulator, combiner);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return stream.collect(supplier, accumulator, combiner);
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return stream.collect(collector);
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return stream.min(comparator);
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return stream.max(comparator);
    }

    @Override
    public long count() {
        return stream.count();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return stream.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return stream.allMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        return stream.noneMatch(predicate);
    }

    @Override
    public Optional<T> findFirst() {
        return stream.findFirst();
    }

    @Override
    public Optional<T> findAny() {
        return stream.findAny();
    }

    @Override
    public Iterator<T> iterator() {
        return stream.iterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return stream.spliterator();
    }

    @Override
    public boolean isParallel() {
        return stream.isParallel();
    }

    @Override
    public ExtendedStream<T> sequential() {
        return new ExtendedStream<>(stream.sequential());
    }

    @Override
    public ExtendedStream<T> parallel() {
        return new ExtendedStream<>(stream.parallel());
    }

    @Override
    public ExtendedStream<T> unordered() {
        return new ExtendedStream<>(stream.unordered());
    }

    @Override
    public ExtendedStream<T> onClose(Runnable closeHandler) {
        return new ExtendedStream<>(stream.onClose(closeHandler));
    }

    @Override
    public void close() {
        stream.close();
    }
}
