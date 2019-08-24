package interview;

public class Pointer {
    public static void main(String[] args) {
        Address a = new Address();
        a.setName("蓉花路");
        change(a);
        System.err.println(a);
    }
    public static void change(Address a) {
        a.setName("北苑路");
        a = new Address();
    }
}
class Address {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Address: " + name;
    }
}