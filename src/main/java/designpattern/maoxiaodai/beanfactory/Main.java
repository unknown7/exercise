package designpattern.maoxiaodai.beanfactory;

import designpattern.maoxiaodai.beanfactory.factory.borrow.BorrowFactory;
import designpattern.maoxiaodai.beanfactory.product.Product;

public class Main {
	private BorrowFactory factory;
	public void setFactory(BorrowFactory factory) {
		this.factory = factory;
	}
	static {
		BeanFactory.init();
	}
	public static void main(String[] args) {
		Main main = (Main) BeanFactory.getBean("main");
		Product product = main.factory.create();
		product.describe();
	}
}
