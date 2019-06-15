package designpattern.factory.normalfactory;

import designpattern.factory.normalfactory.factory.PlaneFactory;
import designpattern.factory.normalfactory.factory.VehicleFactory;
import designpattern.factory.normalfactory.product.Vehicle;

public class Main {
	public static void main(String[] args) {
		VehicleFactory factory = new PlaneFactory();
		Vehicle v = factory.create();
		v.run();
	}
}
