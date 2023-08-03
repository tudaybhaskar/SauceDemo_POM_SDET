package javaBasics;

abstract class Vehicle {
	public abstract int getNoOfWheels();
	
	public void displayNameOfVehicle() {
		System.out.println("Name of the Vehicle is Pulsar 220F");
	}
}
public class AbstractClassDemo1 extends Vehicle {
	
	
	AbstractClassDemo1 ab =  new AbstractClassDemo1();
	@Override
	public int getNoOfWheels() {
		// TODO Auto-generated method stub
		return 2;
	}
	public void displayNameOfVehicle() {
		System.out.println("Name of the Vehicle is Pulsar 220F - blue Color: " + getNoOfWheels());
	}
	
	

}
