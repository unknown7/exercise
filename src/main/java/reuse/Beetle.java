package reuse;

public class Beetle extends Insect {
    private int k = printInit("Beetle.k initialized");
    public Beetle() {
        System.err.println("k= " + k);
        System.err.println("j= " + j);
    }
    private static int x2 = printInit("static Beetle.x2 initialized");

    public static void main(String[] args) {
        System.err.println("Beetle constructor");
//        Beetle beetle = new Beetle();
    }
}
class Insect {
    private int i = 9;
    protected int j;
    Insect() {
        System.err.println("i= " + i + ", j=" + j);
        j = 39;
    }
    private static int x1 = printInit("static Insect.x1 initialized");
    static int printInit(String s) {
        System.err.println(s);
        return 47;
    }
}
