package designpattern.maoxiaodai.abstractfactory.factory.bribery;

import designpattern.maoxiaodai.abstractfactory.product.bribery.Bribery;
import designpattern.maoxiaodai.abstractfactory.product.bribery.NormalBribery;

public class NormalBriberyFactory implements BriberyFactory {
	@Override
	public Bribery create() {
		return new NormalBribery();
	}
}
