package algorithm.algorithms.chapter1.stack;

public class ReverseLinked {

    public static void main(String[] args) {

        int size = 20;
        int i = 1;
        Node<Integer> first = new Node<>();
        Node<Integer> current = first;
        current.value = 0;
        while (i < size) {
            Node<Integer> node = new Node<>();
            node.value = i;
            current.next = node;
            current = node;
            i++;
        }

        Node<Integer> node = first;
        while (node != null) {
            System.err.print(node.value);
            System.err.print(" ");
            node = node.next;
        }
        System.err.println();

        Node<Integer> reverse = reverse(first);
        Node<Integer> backToOrigin = reverse(reverse, reverse.next);

        while (backToOrigin != null) {
            System.err.print(backToOrigin.value);
            System.err.print(" ");
            backToOrigin = backToOrigin.next;
        }
    }

    private static Node<Integer> reverse(Node<Integer> first) {
        Node<Integer> second = first.next;
        first.next = null;
        Node<Integer> third = second.next;
        while (second != null) {
            second.next = first;
            first = second;
            second = third;
            if (third != null)
                third = third.next;
        }
        return first;
    }

    private static Node<Integer> reverse(Node<Integer> first, Node<Integer> second) {
        if (second == null)
            return first;
        Node<Integer> next = second.next;
        second.next = first;
        if (first.next == second)
            first.next = null;
        return reverse(second, next);
    }

    private static class Node<T> {
        T value;
        Node<T> next;
    }
}
