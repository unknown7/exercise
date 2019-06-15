package designpattern.maoxiaodai.abstractfactory.factory.borrow;

import designpattern.maoxiaodai.abstractfactory.factory.ProductFactory;
import designpattern.maoxiaodai.abstractfactory.product.borrow.Borrow;

public interface BorrowFactory extends ProductFactory {
	Borrow create();
}
