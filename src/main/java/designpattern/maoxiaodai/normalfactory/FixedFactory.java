package designpattern.maoxiaodai.normalfactory;

public class FixedFactory implements BorrowFactory {
	public Fixed create() {
		return new Fixed();
	}
}
