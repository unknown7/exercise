package algorithm.algorithms.chapter1.stack;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public class Parenthesis {

    private final static String EMPTY = "";

    private final static String[] C = {"()", "[]", "{}"};

    private final static Couple[] COUPLES = new Couple[C.length];

    static {
        for (int i = 0; i < C.length; i++) {
            COUPLES[i] = new Couple(C[i]);
        }
    }

    private boolean isPalindromic(String str) {
        Stack<String> stack = new LinkedStack<>();
        String[] split = str.split(EMPTY);
        for (String s : split) {
            if (Arrays.stream(COUPLES).anyMatch(item -> item.getSide(s) == Couple.Side.LEFT)) {
                stack.push(s);
            } else {
                String pop = stack.pop();
                if (Arrays.stream(COUPLES).noneMatch(item -> item.isCouple(pop, s))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "{()}(){[()]}";
        Parenthesis p = new Parenthesis();
        boolean palindromic = p.isPalindromic(str);
        System.err.println(palindromic);
    }

    private static class Couple {

        public enum Side {
            LEFT, RIGHT
        }

        private String left;

        private String right;

        Couple(String str) {
            assert !StringUtils.isEmpty(str);
            String[] split = str.split(EMPTY);
            left = split[0];
            right = split[1];
        }

        boolean isCouple(String left, String right) {
            return this.left.equals(left) && this.right.equals(right);
        }

        Side getSide(String c) {
            return left.equals(c) ? Side.LEFT : Side.RIGHT;
        }
    }
}
