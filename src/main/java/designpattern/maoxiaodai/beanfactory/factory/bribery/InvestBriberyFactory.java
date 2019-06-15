package designpattern.maoxiaodai.beanfactory.factory.bribery;

import designpattern.maoxiaodai.abstractfactory.product.bribery.Bribery;
import designpattern.maoxiaodai.abstractfactory.product.bribery.InvestBribery;

public class InvestBriberyFactory implements BriberyFactory {
	@Override
	public Bribery create() {
		return new InvestBribery();
	}
}
