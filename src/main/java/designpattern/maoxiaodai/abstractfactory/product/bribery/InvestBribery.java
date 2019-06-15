package designpattern.maoxiaodai.abstractfactory.product.bribery;

public class InvestBribery implements Bribery {
	@Override
	public void describe() {
		System.err.println("我是投资红包");
	}
}
