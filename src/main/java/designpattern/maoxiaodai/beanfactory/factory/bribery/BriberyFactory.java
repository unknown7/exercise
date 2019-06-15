package designpattern.maoxiaodai.beanfactory.factory.bribery;

import designpattern.maoxiaodai.abstractfactory.factory.ProductFactory;
import designpattern.maoxiaodai.abstractfactory.product.bribery.Bribery;

public interface BriberyFactory extends ProductFactory {
	Bribery create();
}
