package algorithm.algorithms.chapter1.queue;

public interface Queue<T> extends Iterable<T> {

    void enqueue(T value);

    T dequeue();

    boolean isEmpty();

    int size();
}
