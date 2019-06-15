package designpattern.factory.simplefactory;

public class VehicleFactory {
	enum VehicleType {
		car, plane, bicycle, exit
	}
	public static Vehicle create(VehicleType vehicleType) {
		Vehicle vehicle = null;
		switch (vehicleType) {
		case car:
			vehicle = new Car();
			break;
		case plane:
			vehicle = new Plane();
			break;
		case bicycle:
			vehicle = new Bicycle();
			break;
		case exit:
			System.exit(0);
			break;
		default:
			break;
		}
		return vehicle;
	}
}
