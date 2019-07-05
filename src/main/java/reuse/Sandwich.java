package reuse;

public class Sandwich extends PortableLunch {
    private Bread bread = new Bread();
    private Cheese cheese = new Cheese();
    private Lettuce lettuce = new Lettuce();
    Sandwich() {
        System.err.println("Sandwich");
    }

    public static void main(String[] args) {
        new Sandwich();
    }
}
class Meal {
    Meal() {
        System.err.println("Meal");
    }
}
class Bread {
    Bread() {
        System.err.println("Bread");
    }
}
class Cheese {
    Cheese() {
        System.err.println("Cheese");
    }
}
class Lettuce {
    Lettuce() {
        System.err.println("Lettuce");
    }
}
class Lunch extends Meal {
    Lunch() {
        System.err.println("Lunch");
    }
}
class PortableLunch extends Lunch {
    PortableLunch() {
        System.err.println("PortableLunch");
    }
}
