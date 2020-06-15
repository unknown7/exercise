package algorithm.algorithms.chapter1.queue;

public class Main {

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(6);
        queue.enqueue(8);
        for (Integer i : queue) {
            System.err.println(i);
        }
    }
}
