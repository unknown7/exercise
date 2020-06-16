package algorithm.algorithms.chapter1.stack;

import com.google.common.collect.Lists;

import java.util.List;

public class InfixToPostfix {

    private static final String EXPRESSION = "((1+2)*((3-4)*(5-6)))";

    private static final String EMPTY = "";

    private static final List<String> OPERATORS = Lists.newArrayList("+", "-", "*", "/");

    public static void main(String[] args) {
        Stack<String> stack = new ArrayStack<>();
        for (String s : EXPRESSION.split(EMPTY)) {
            if (")".equals(s)) {
                System.err.print(stack.pop());
            } else if (OPERATORS.contains(s)) {
                stack.push(s);
            } else if (!"(".equals(s)) {
                System.err.print(s);
            }
        }
    }
}
