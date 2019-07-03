package reuse;

public class Constructors {
//    Constructors() {
//        System.err.println("Constructors..");
//    }
    Constructors(int i) {
        System.err.println("Constructor..(int i)");
    }

    public static void main(String[] args) {
        new Constructor(1);
    }
}

class Constructor extends Constructors {
    Constructor() {
        super(1);
        System.err.println("Constructor..");
    }
    Constructor(int i) {
        super(i);
        System.err.println("Constructor..(int i)");
    }
}
