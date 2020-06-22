package algorithm;

public class Egg {

    public static void main(String[] args) {
        try {
            for (int i = 9; ; i++) {
                if (
                    i % 1 == 0 &&
                    i % 2 == 1 &&
                    i % 3 == 0 &&
                    i % 4 == 1 &&
                    i % 5 == 4 &&
                    i % 6 == 3 &&
                    i % 7 == 0 &&
                    i % 8 == 1 &&
                    i % 9 == 0
                ) {
                    System.err.println("筐里最少有" + i + "个鸡蛋。");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
