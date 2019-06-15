package designpattern.maoxiaodai.normalfactory;

public class CheckingFactory implements BorrowFactory {
	public Checking create() {
		return new Checking();
	}
}
