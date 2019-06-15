package designpattern.factory.normalfactory.product;


public class Car implements Vehicle {
	@Override
	public void run() {
		System.err.println("car running...");
	}
}
