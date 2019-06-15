package designpattern.maoxiaodai.beanfactory.factory.borrow;

import designpattern.maoxiaodai.beanfactory.product.borrow.Borrow;
import designpattern.maoxiaodai.beanfactory.product.borrow.Fixed;

public class FixedFactory implements BorrowFactory {
	@Override
	public Borrow create() {
		return new Fixed();
	}
}
