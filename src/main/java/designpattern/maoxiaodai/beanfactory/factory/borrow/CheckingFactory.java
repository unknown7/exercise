package designpattern.maoxiaodai.beanfactory.factory.borrow;

import designpattern.maoxiaodai.beanfactory.product.borrow.Borrow;
import designpattern.maoxiaodai.beanfactory.product.borrow.Checking;

public class CheckingFactory implements BorrowFactory {
	@Override
	public Borrow create() {
		return new Checking();
	}
}
