package reuse;

public interface Interface {
    int MAX = 0;
    void p();
}
class InterfaceExt implements Interface {

    @Override
    public void p() {
        System.err.println(MAX);
    }

    public static void main(String[] args) {
        Interface i = new InterfaceExt();
        i.p();
    }
}