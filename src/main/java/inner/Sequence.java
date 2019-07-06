package inner;

interface Selector {
    boolean end();
    Object current();
    void next();
}
public class Sequence {
    private int[] items;
    private int cur;
    public Sequence(int size) {
        items = new int[size];
    }
    public void add(int x) {
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
    public Selector selector() {
        return new SequenceSelector();
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
    }
}
