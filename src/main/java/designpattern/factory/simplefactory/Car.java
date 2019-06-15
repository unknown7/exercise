package designpattern.factory.simplefactory;

public class Car implements Vehicle {
	@Override
	public void run() {
		System.err.println("A car running...");
	}
}
