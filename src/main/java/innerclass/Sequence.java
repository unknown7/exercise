package innerclass;

import java.nio.channels.SelectionKey;

interface Selector {
    boolean end();
    Object current();
    void next();
}
public class Sequence {
    private Object[] items;
    private int cur;
    public Sequence(int size) {
        items = new Object[size];
    }
    public void add(Object x) {
        if (cur < items.length) {
            items[cur++] = x;
        }
    }
    private class SequenceSelector implements Selector {
        private int i;

        @Override
        public boolean end() {
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i < items.length)
                i++;
        }
    }
    private class ReverseSequenceSelector implements Selector {
        private int i = items.length - 1;

        @Override
        public boolean end() {
            return i == -1;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i >= 0)
                i--;
        }
    }
    public Selector selector() {
        return new SequenceSelector();
    }
    public Selector reverseSelector() {
        return new ReverseSequenceSelector();
    }

    public static void main(String[] args) {
        final int SIZE = 10;
        Sequence sequence = new Sequence(SIZE);
        for (int i = 0; i < SIZE; i++) {
            sequence.add(i);
        }
        Selector selector = sequence.selector();
        while (!selector.end()) {
            System.err.print(selector.current() + " ");
            selector.next();
        }
        System.err.println();
        Selector reverse = sequence.reverseSelector();
        while (!reverse.end()) {
            System.err.print(reverse.current() + " ");
            reverse.next();
        }

        System.err.println();

        Sequence sequence2 = new Sequence(SIZE);
        for (int i = 0; i < SIZE; i++) {
            sequence2.add("a" + i);
        }
        Selector selector2 = sequence2.selector();
        while (!selector2.end()) {
            System.err.print(selector2.current() + " ");
            selector2.next();
        }
    }
}
