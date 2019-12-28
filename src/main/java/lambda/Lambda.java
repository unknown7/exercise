package lambda;

import java.util.concurrent.Callable;

public class Lambda {

    public static void main(String[] args) {
        Runnable r = () -> {
            System.err.println("r");
        };
        Callable<String> c1 = () -> "c1";
        Callable<Integer> c2 = () -> {
            System.err.println(2);
            return 2;
        };
        double c3Temp = 1.1;
        Callable<Double> c3 = () -> c3Temp + 1.1;
    }
}
