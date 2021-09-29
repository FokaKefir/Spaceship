package com.fokakefir.spaceship.model;

import java.lang.StringBuilder;

public class Ship{
	private int deviation;
	private int thrust;
	private int voltage;
	private int reactorTemperature;
	private int waterPressure;
	private int oxygenLevel;
	private boolean thruster;
	private boolean communication;
	private static Ship instance = null;

	private Ship(int deviation, int thrust, int voltage,
		int reactorTemperature, int waterPressure, int oxygenLevel){
		this.deviation = deviation;
		this.thrust = thrust;
		this.voltage = voltage;
		this.reactorTemperature = reactorTemperature;
		this.waterPressure = waterPressure;
		this.oxygenLevel = oxygenLevel;
		this.thruster = true;
		this.communication = true;
	}

	public static Ship getInstance(){
		if(instance == null)
			instance = new Ship(0,0,2,1,2,2);
		return instance; 
	}

	public boolean getThruster(){
		return thruster;
	}
	public boolean getCommunication(){
		return communication;
	}
	public int getDeviation(){
		return deviation;
	}
	public int getThrust(){
		return thrust;
	}
	public int getVoltage(){
		return voltage;
	}
	public int getReactorTemperature(){
		return reactorTemperature;
	}
	public int getWaterPressure(){
		return waterPressure;
	}
	public int getOxygenLevel(){
		return oxygenLevel;
	}

	public void raiseThrust(){
		Ship s = getInstance();
		s.thrust++;
	}

	public void lowerThrust(){
		Ship s = getInstance();
		s.thrust--;
	}

	public void raiseVoltage(){
		if(voltage > 2) return;

		Ship s = getInstance();
		s.voltage++;
	}

	public void lowerVoltage(){
		if(voltage < 1) return;

		Ship s = getInstance();
		s.voltage--;
	}

	public void raiseOxygenLevel(){
		if(oxygenLevel>2) return;

		Ship s = getInstance();
		oxygenLevel++;
	}

	public void lowerOxygenLevel(){
		if(oxygenLevel < 1) return;

		Ship s = getInstance();
		oxygenLevel--;
	}

	public void raiseReactorTemperature(){
		if(reactorTemperature>2) return;

		Ship s = getInstance();
		s.reactorTemperature++;
	}

	public void lowerReactorTemperature(){
		if(reactorTemperature < 1) return;

		Ship s = getInstance();
		s.reactorTemperature--;
	}

	public void raiseWaterPressure(){
		if(waterPressure > 2) return;

		Ship s = getInstance();
		s.waterPressure++;
	}

	public void lowerWaterPressure(){
		if(waterPressure < 1) return;
		
		Ship s = getInstance();
		s.waterPressure--;
	}

	public void mendThruster(){
		thruster = true;
	}

	public void mendCommunication(){
		communication = true;
	}

	public void breakThruster(){
		thruster = false;
	}

	public void breakCommunication(){
		communication = false;
	}

	public String toString(){
		Ship s = getInstance();	
		StringBuilder str = new StringBuilder();	

		str.append("[");

		str.append("\nCommunication:");
		if(communication)
			str.append("ONLINE");
		else
			str.append("OFFLINE");

		str.append("\nDeviation:");
		str.append(s.deviation);

		str.append("\nThrust:");
		if(thruster)
			str.append(s.thrust);
		else
			str.append("\nThruster Broken");

		str.append("\nVoltage:");
		str.append(s.voltage);

		str.append("\nReactorTemperature:");
		str.append(s.reactorTemperature);

		str.append("\nWaterPressure:");
		str.append(s.waterPressure);

		str.append("\nOxygenLevel:");
		str.append(s.oxygenLevel);

		str.append("\n]");

		return str.toString();
	}
}
