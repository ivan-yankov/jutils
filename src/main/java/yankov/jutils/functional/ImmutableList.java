package yankov.jutils.functional;

import yankov.jutils.functional.tuples.Tuple;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ImmutableList<T> implements List<T> {
    private final List<T> list;

    public ImmutableList(List<T> list) {
        this.list = List.copyOf(list);
    }

    public static <A> ImmutableList<A> of(List<A> l) {
        return new ImmutableList<>(l);
    }

    @SafeVarargs
    public static <A> ImmutableList<A> from(A... array) {
        return new ImmutableList<>(List.of(array));
    }

    public static <A> ImmutableList<ImmutableList<A>> fromArray2d(A[][] array) {
        List<ImmutableList<A>> result = new ArrayList<>();
        for (A[] a : array) {
            result.add(ImmutableList.from(a));
        }
        return ImmutableList.of(result);
    }

    public static <A> ImmutableList<ImmutableList<A>> fromList2d(List<List<A>> list) {
        return ImmutableList.of(list).stream().map(ImmutableList::of).toList();
    }

    public static <A> ImmutableList<A> fill(int n, A element) {
        List<A> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(element);
        }
        return ImmutableList.of(result);
    }

    public long numberOfSlides(long step) {
        return Math.round(Math.ceil((double) size() / (double) step));
    }

    public ImmutableList<ImmutableList<T>> sliding(long step) {
        List<ImmutableList<T>> result = new ArrayList<>();
        for (int i = 0; i < numberOfSlides(step); i++) {
            result.add(stream().skip(i * step).limit(step).toList());
        }
        return ImmutableList.of(result);
    }

    public List<T> asMutable() {
        return new ArrayList<>(list);
    }

    @SafeVarargs
    public final ImmutableList<T> append(T... s) {
        List<T> result = asMutable();
        result.addAll(ImmutableList.from(s));
        return ImmutableList.of(result);
    }

    public ImmutableList<T> insert(int index, T element) {
        if (index < 0 || index > size()) {
            return this;
        }
        if (index == 0 && isEmpty()) {
            return ImmutableList.from(element);
        }
        List<T> l = stream().limit(index).toList().append(element);
        return ExtendedStream.concat(l.stream(), stream().skip(index)).toList();
    }

    public ImmutableList<T> removeElement(int index) {
        if (index < 0 || index >= size()) {
            return this;
        }
        return ExtendedStream.concat(stream().limit(index), stream().skip(index + 1)).toList();
    }

    public ImmutableList<T> updateElement(int index, T element) {
        return zipWithIndex().stream().map(x -> x._2() == index ? element : x._1()).toList();
    }

    public <U> ImmutableList<Tuple<T, U>> zip(ImmutableList<U> other) {
        int n = Math.min(size(), other.size());
        ImmutableList<T> a = stream().limit(n).toList();
        ImmutableList<U> b = other.stream().limit(n).toList();
        return a.zipWithIndex().stream().map(x -> new Tuple<>(x._1(), b.get(x._2()))).toList();
    }

    public ImmutableList<Tuple<T, Integer>> zipWithIndex() {
        List<Tuple<T, Integer>> result = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            result.add(new Tuple<>(get(i), i));
        }
        return ImmutableList.of(result);
    }

    public String mkString(String separator, Function<T, String> print) {
        return stream().map(print).collect(Collectors.joining(separator));
    }

    public boolean eq(ImmutableList<T> other) {
        if (size() == other.size()) {
            return zip(other).stream().allMatch(x -> x._1().equals(x._2()));
        }
        return false;
    }

    @Override
    public ExtendedStream<T> stream() {
        return new ExtendedStream<>(list.stream());
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <A> A[] toArray(A[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException(getErrorMessage("Add"));
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException(getErrorMessage("Remove"));
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException(getErrorMessage("Add all"));
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException(getErrorMessage("Add all"));
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(getErrorMessage("Remove all"));
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(getErrorMessage("Retain all"));
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException(getErrorMessage("Clear"));
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException(getErrorMessage("Set"));
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException(getErrorMessage("Add"));
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException(getErrorMessage("Remove"));
    }

    private String getErrorMessage(String operation) {
        return operation + " is not supported on immutable list.";
    }
}
