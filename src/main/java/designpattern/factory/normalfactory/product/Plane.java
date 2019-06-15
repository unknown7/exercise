package designpattern.factory.normalfactory.product;


public class Plane implements Vehicle {
	@Override
	public void run() {
		System.err.println("a plane flying...");
	}
}
