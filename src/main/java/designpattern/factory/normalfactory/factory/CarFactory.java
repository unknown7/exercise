package designpattern.factory.normalfactory.factory;

import designpattern.factory.normalfactory.product.Car;
import designpattern.factory.normalfactory.product.Vehicle;

public class CarFactory implements VehicleFactory {
	@Override
	public Vehicle create() {
		return new Car();
	}
}
