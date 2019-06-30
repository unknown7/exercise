package initialandcleanup;

public class Up {
    public Up(float x) {
        System.err.println("float " + x);
    }
    public Up(double x) {
        System.err.println("double " + x);
    }

    public static void main(String[] args) {
        int x = 5;
        new Up(x);
    }
}
