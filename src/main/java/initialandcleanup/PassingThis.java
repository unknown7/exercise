package initialandcleanup;

public class PassingThis {
    public static void main(String[] args) {
        new Person().eat(new Apple());
    }
}
class Person {
    public void eat(Apple apple) {
        Apple peeled = apple.getPeeled();
        System.err.println("Yummy");
    }
}
class Apple {
    Apple getPeeled() {
        return Peeler.peel(this);
    }
}
class Peeler {
    static Apple peel(Apple apple) {
        System.err.println("peeled...");
        return apple;
    }
}