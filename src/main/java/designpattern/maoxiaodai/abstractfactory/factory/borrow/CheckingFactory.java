package designpattern.maoxiaodai.abstractfactory.factory.borrow;

import designpattern.maoxiaodai.abstractfactory.product.borrow.Borrow;
import designpattern.maoxiaodai.abstractfactory.product.borrow.Checking;

public class CheckingFactory implements BorrowFactory {
	@Override
	public Borrow create() {
		return new Checking();
	}
}
