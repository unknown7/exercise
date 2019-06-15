package designpattern.factory.simplefactory;

import java.io.IOException;

import designpattern.factory.simplefactory.VehicleFactory.VehicleType;

public class Main extends ResourceProvidor {
	@Override
	public void handle(String read) throws IOException {
		VehicleType vehicleType = VehicleType.valueOf(read);
		Vehicle vehicle = VehicleFactory.create(vehicleType);
		vehicle.run();
	}
	public static void main(String[] args) {
		new Main();
	}
}
