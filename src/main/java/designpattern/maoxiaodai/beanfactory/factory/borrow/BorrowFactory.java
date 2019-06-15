package designpattern.maoxiaodai.beanfactory.factory.borrow;

import designpattern.maoxiaodai.beanfactory.factory.ProductFactory;
import designpattern.maoxiaodai.beanfactory.product.borrow.Borrow;

public interface BorrowFactory extends ProductFactory {
	Borrow create();
}
