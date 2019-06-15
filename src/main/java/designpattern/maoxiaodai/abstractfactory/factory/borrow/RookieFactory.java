package designpattern.maoxiaodai.abstractfactory.factory.borrow;

import designpattern.maoxiaodai.abstractfactory.product.borrow.Borrow;
import designpattern.maoxiaodai.abstractfactory.product.borrow.Rookie;

public class RookieFactory implements BorrowFactory {
	@Override
	public Borrow create() {
		return new Rookie();
	}
}
