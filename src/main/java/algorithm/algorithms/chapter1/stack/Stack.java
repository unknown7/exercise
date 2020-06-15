package algorithm.algorithms.chapter1.stack;

public interface Stack<T> extends Iterable<T> {

    void push(T value);

    T pop();

    boolean isEmpty();

    int size();
}
