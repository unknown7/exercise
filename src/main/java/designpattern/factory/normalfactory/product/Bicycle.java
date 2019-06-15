package designpattern.factory.normalfactory.product;


public class Bicycle implements Vehicle {
	@Override
	public void run() {
		System.err.println("ridding a bicycle...");
	}
}
