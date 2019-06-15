package designpattern.factory.normalfactory.factory;

import designpattern.factory.normalfactory.product.Bicycle;
import designpattern.factory.normalfactory.product.Vehicle;

public class BicycleFactory implements VehicleFactory {
	@Override
	public Vehicle create() {
		return new Bicycle();
	}
}
