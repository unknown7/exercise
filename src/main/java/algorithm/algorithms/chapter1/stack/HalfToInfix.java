package algorithm.algorithms.chapter1.stack;

public class HalfToInfix {

    private static final String EXPRESSION = "1+2)*3-4)*5-6)))";

    private static final String EMPTY = "";

    public static void main(String[] args) {
        Stack<String> stack = new LinkedStack<>();
        for (String s : EXPRESSION.split(EMPTY)) {
            if (")".equals(s)) {
                String right = stack.pop();
                String operator = stack.pop();
                String left = stack.pop();
                stack.push("(" + left + operator + right + ")");
            } else {
                stack.push(s);
            }
        }
        System.err.println(stack.pop());
    }
}
