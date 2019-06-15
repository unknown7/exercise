package innerclass;

public class Outer {
	private static int age = 12;
	private static class Inner {
		public void print() {
			System.err.println(age);
		}
	}
	public void print() {
		new Inner().print();
	}
	public static void main(String[] args) {
//		Outer o = new Outer();
//		o.print();
		new Inner().print();
	}
}
