package algorithm.algorithms;

public class Arithmetic {

    private enum Operator {
        PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"), LEFT_INTERVAL("("), RIGHT_INTERVAL(")"), DEFAULT("default");

        private String operator;

        Operator(String operator) {
            this.operator = operator;
        }

        public static Operator get(String operator) {
            for (Operator value : values()) {
                if (value.operator.equals(operator)) {
                    return value;
                }
            }
            return DEFAULT;
        }
    }

    private static final String EXPRESSION = "(((1+2)+(3+4))*(5*6))/((2*7)+1))";

    public static void main(String[] args) {
        Stack<Operator> operators = new Stack<Operator>();
        Stack<Integer> numbers = new Stack<Integer>();

        for (int i = 0; i < EXPRESSION.length(); i++) {
            String value = String.valueOf(EXPRESSION.charAt(i));
            switch (Operator.get(value)) {
                case PLUS:
                    operators.push(Operator.PLUS);
                    break;
                case MINUS:
                    operators.push(Operator.MINUS);
                    break;
                case MULTIPLY:
                    operators.push(Operator.MULTIPLY);
                    break;
                case DIVIDE:
                    operators.push(Operator.DIVIDE);
                    break;
                case LEFT_INTERVAL:
                    break;
                case RIGHT_INTERVAL:
                    Operator operator = operators.pop();
                    Integer sum = 0;
                    Integer num = numbers.pop();
                    switch (operator) {
                        case PLUS:
                            sum = numbers.pop() + num;
                            break;
                        case MINUS:
                            sum = numbers.pop() - num;
                            break;
                        case MULTIPLY:
                            sum = numbers.pop() * num;
                            break;
                        case DIVIDE:
                            sum = numbers.pop() / num;
                            break;
                    }
                    numbers.push(sum);
                    break;
                default:
                    numbers.push(Integer.valueOf(value));
                    break;
            }
        }
        System.err.println(numbers.pop());
    }
}
