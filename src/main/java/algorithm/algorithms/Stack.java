package algorithm.algorithms;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class Stack<Item> {
    private Item[] arr = (Item[]) new Object[10];
    private int size = 0;

    public void push(Item item) {
        ensureCapacity();
        arr[size++] = item;
    }

    public Item pop() {
        if (!isEmpty())
            return arr[--size];
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size + 1 >= arr.length) {
            Item[] newArr = Arrays.copyOf(arr, size << 1);
            arr = newArr;
        }
    }

    public static void main(String[] args) {
        String json = "{\"age\":28,\"id\":\"1\",\"name\":\"Jeff\"}";
        Vo vo = JSON.parseObject(json, Vo.class, EncryptParserConfig.global, null, JSON.DEFAULT_PARSER_FEATURE);
        System.err.println(vo.getName());
    }
}
