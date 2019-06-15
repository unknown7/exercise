package designpattern.maoxiaodai.abstractfactory.product.bribery;

public class NormalBribery implements Bribery {
	@Override
	public void describe() {
		System.err.println("我是普通红包");
	}
}
