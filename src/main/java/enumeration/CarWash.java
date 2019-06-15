package enumeration;

import java.util.EnumSet;

public class CarWash {
	public enum Cycle {
		UNDERBODY {
			@Override
			void action() {
				System.err.println("Spraying the under body");
			}
		}, WHEELWASH {
			@Override
			void action() {
				System.err.println("Washing the wheels");				
			}
		}, PREWASH {
			@Override
			void action() {
				System.err.println("Loosening the dirt");				
			}
		}, BASIC {
			@Override
			void action() {
				System.err.println("The basic wash");				
			}
		}, HOTWAX {
			@Override
			void action() {
				System.err.println("Applying hot wax");				
			}
		}, RINSE {
			@Override
			void action() {
				System.err.println("Rinsing");				
			}
		}, BLOWDRY {
			@Override
			void action() {
				System.err.println("Blowing dry");				
			}
		};
		abstract void action();
	}
	EnumSet<Cycle> cycles = EnumSet.of(Cycle.BASIC, Cycle.RINSE);
	public void add(Cycle cycle) {
		cycles.add(cycle);
	}
	public void washCar() {
		for (Cycle cycle : cycles)
			cycle.action();
	}
	@Override
	public String toString() {
		return cycles.toString();
	}
	
	public static void main(String[] args) {
		CarWash wash = new CarWash();
		System.err.println(wash);
		wash.washCar();
		
		wash.add(Cycle.BLOWDRY);
		wash.add(Cycle.BLOWDRY);
		wash.add(Cycle.RINSE);
		wash.add(Cycle.HOTWAX);
		System.err.println(wash);
		wash.washCar();
	}
}
