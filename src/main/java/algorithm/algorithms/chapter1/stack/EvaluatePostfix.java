package algorithm.algorithms.chapter1.stack;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EvaluatePostfix {

    private static final String EXPRESSION = "12+34-56-**";

    private static final String EMPTY = "";

    private static final List<String> OPERATORS = Lists.newArrayList("+", "-", "*", "/");

    public static void main(String[] args) {
        Stack<Double> stack = new ArrayStack<>();
        for (String s : EXPRESSION.split(EMPTY)) {
            if (OPERATORS.contains(s)) {
                Double left = stack.pop();
                Double right = stack.pop();
                double result = Operator.getByCharacter(s).orElseThrow(RuntimeException::new).operator.operate(left, right);
                stack.push(result);
            } else {
                stack.push(Double.valueOf(s));
            }
        }
        System.err.println(stack.pop());
    }

    private enum Operator {
        PLUS("+", new Plus()), MINUS("-", new Minus()), MULTIPLY("*", new Multiply()), DIVIDE("/", new Divide());

        private String character;

        private Operate operator;

        Operator(String character, Operate operator) {
            this.character = character;
            this.operator = operator;
        }

        public static Optional<Operator> getByCharacter(String character) {
            Optional<Operator> result = Arrays.stream(values()).filter(item -> item.character.equals(character)).findFirst();
            return result;
        }

        private interface Operate {
            double operate(Double left, Double right);
        }

        private static class Plus implements Operate {
            public double operate(Double left, Double right) {
                return left + right;
            }
        }
        private static class Minus implements Operate {
            public double operate(Double left, Double right) {
                return left - right;
            }
        }
        private static class Multiply implements Operate {
            public double operate(Double left, Double right) {
                return left * right;
            }
        }
        private static class Divide implements Operate {
            public double operate(Double left, Double right) {
                return left / right;
            }
        }
    }
}
