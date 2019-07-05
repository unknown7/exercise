package reuse;

public class Sandwich extends PortableLunch {
    public int i = 1;
    private Bread bread = new Bread();
    private Cheese cheese = new Cheese();
    private Lettuce lettuce = new Lettuce();
    Sandwich() {
        System.err.println("Sandwich");
    }

    public static void main(String[] args) {
        Meal meal = new Sandwich();
        System.err.println(meal.i);
    }
}
class Meal {
    public int i = 0;
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
