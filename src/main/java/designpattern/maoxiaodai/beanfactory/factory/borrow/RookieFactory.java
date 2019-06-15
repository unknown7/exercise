package designpattern.maoxiaodai.beanfactory.factory.borrow;

import designpattern.maoxiaodai.beanfactory.product.borrow.Borrow;
import designpattern.maoxiaodai.beanfactory.product.borrow.Rookie;

public class RookieFactory implements BorrowFactory {
	@Override
	public Borrow create() {
		return new Rookie();
	}
}
