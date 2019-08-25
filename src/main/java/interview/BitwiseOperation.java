package interview;

public class BitwiseOperation {
    public static void main(String[] args) {
        int a = 128;
        int b = 129;
        System.err.println("a&b=" + (a & b));
        System.err.println("a|b=" + (a | b));
        System.err.println("a^b=" + (a ^ b));
        int c = 2;
        System.err.println("~c=" + ~c);
    }
}
