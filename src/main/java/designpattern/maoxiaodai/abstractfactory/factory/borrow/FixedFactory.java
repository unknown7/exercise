package designpattern.maoxiaodai.abstractfactory.factory.borrow;

import designpattern.maoxiaodai.abstractfactory.product.borrow.Borrow;
import designpattern.maoxiaodai.abstractfactory.product.borrow.Fixed;

public class FixedFactory implements BorrowFactory {
	@Override
	public Borrow create() {
		return new Fixed();
	}
}
