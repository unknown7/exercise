package designpattern.factory.normalfactory.factory;

import designpattern.factory.normalfactory.product.Plane;
import designpattern.factory.normalfactory.product.Vehicle;

public class PlaneFactory implements VehicleFactory {
	@Override
	public Vehicle create() {
		return new Plane();
	}
}
