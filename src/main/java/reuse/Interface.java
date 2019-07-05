package reuse;

public interface Interface {
    int MAX = 0;
    void p();
}
abstract class InterfaceExtAbs implements Interface {
    @Override
    public void p() {
        System.err.println(MAX);
        System.err.println(this.toString());
    }

    @Override
    public String toString() {
        return "InterfaceExtAbs";
    }
}
class InterfaceExt extends InterfaceExtAbs {
    public static void main(String[] args) {
        Interface i = new InterfaceExt();
        i.p();
    }

    @Override
    public String toString() {
        return "InterfaceExt";
    }
}