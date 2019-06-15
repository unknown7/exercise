package designpattern.factory.simplefactory;

public class Plane implements Vehicle {
	@Override
	public void run() {
		System.err.println("plane flying...");
	}
}
