package designpattern.maoxiaodai.normalfactory;

public class Main {
	public static void main(String[] args) {
		BorrowFactory factory = new CheckingFactory();
		Borrow borrow = factory.create();
		borrow.describe();
	}
}
