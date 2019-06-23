package interview;

import java.util.Arrays;

public class Constructor {
	static final int[] arr = new int[5];
	public static void main(String[] args) {
//		arr = new int[7];
		arr[1] = 6;
		Child c = new Child(1);
		System.err.println(Arrays.toString(arr));
	}
}
class Parent {
	int id;
	public Parent() {
		System.err.println("Parent()");
	}
	public Parent(int id) {
		this.id = id;
		System.err.println("Parent(int id)");
	}
}
class Child extends Parent {
	public Child() {
		System.err.println("Child()");
	}
	public Child(int id) {
		System.err.println("Child(int id)");
	}
}