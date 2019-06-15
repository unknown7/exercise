package designpattern.maoxiaodai.normalfactory;

public class RookieFactory implements BorrowFactory {
	public Rookie create() {
		return new Rookie();
	}
}
