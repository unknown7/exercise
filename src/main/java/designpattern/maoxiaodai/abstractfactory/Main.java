package designpattern.maoxiaodai.abstractfactory;

import designpattern.maoxiaodai.abstractfactory.factory.ProductFactory;
import designpattern.maoxiaodai.abstractfactory.factory.borrow.RookieFactory;
import designpattern.maoxiaodai.abstractfactory.factory.bribery.InvestBriberyFactory;
import designpattern.maoxiaodai.abstractfactory.product.Product;

public class Main {
	public static void main(String[] args) {
		ProductFactory factory = new RookieFactory();
		Product product = factory.create();
		product.describe();
		
		factory = new InvestBriberyFactory();
		product = factory.create();
		product.describe();
	}
}
