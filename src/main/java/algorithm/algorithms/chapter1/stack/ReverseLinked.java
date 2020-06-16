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
        while (reverse != null) {
            System.err.print(reverse.value);
            System.err.print(" ");
            reverse = reverse.next;
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

    private static class Node<T> {
        T value;
        Node<T> next;
    }
}
