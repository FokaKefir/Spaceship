package com.fokakefir.spaceship.logic;


import com.fokakefir.spaceship.model.Alert;
import com.fokakefir.spaceship.model.HealthData;
import com.fokakefir.spaceship.model.OrbitalData;
import com.fokakefir.spaceship.model.Player;
import com.fokakefir.spaceship.model.Ship;
import com.fokakefir.spaceship.model.SystemData;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public class GameService{
	private int time;
	private int minutes;
	private int nextFlare;
	private int flareDuration;
	private Ship ship;
	private Player player;
	private int SAFE_DEVIATION;

	public GameService(){
		time = 0;	
		minutes = 0;
		ship = ship.getInstance();
		player = new Player(4);
		SAFE_DEVIATION = 100;
		nextFlare = (int)(Math.random()*5) + 17;
	}	

	public Player getPlayer(){
		return player;
	}

	private void fire(){
		int r = (int)(Math.random()*3);
		switch(r){
			case 0:ship.lowerVoltage();break;
			case 1:ship.raiseWaterPressure();break;
			case 2:ship.lowerOxygenLevel();break;
		}
	}

	public int tick(){
		if((ship.getDeviation()<-SAFE_DEVIATION) || (ship.getDeviation()>SAFE_DEVIATION)){
			if(Math.random()<0.1)ship.lowerWaterPressure();
			if(Math.random()<0.1)ship.lowerOxygenLevel();
			if(Math.random()<0.1)player.damage();
			if(Math.random()<0.1)ship.breakCommunication();
			if(Math.random()<0.1)ship.breakThruster();	
		}
		
		if(ship.getVoltage()==3 && ship.getOxygenLevel() == 2)
			if(Math.random()<0.1)fire();
		if(ship.getVoltage()==3 && ship.getOxygenLevel() == 3)
			if(Math.random()<0.4)fire();	
			if(Math.random()<0.05)fire();	
		if(ship.getVoltage() == 1)
			if(Math.random()<0.2)ship.lowerWaterPressure();
		if(ship.getVoltage() == 1)
			if(Math.random()<0.2)ship.lowerOxygenLevel();
		if(ship.getWaterPressure() == 3)
			if(Math.random()<0.2)ship.lowerOxygenLevel();
		if(ship.getWaterPressure() == 1)
			if(Math.random()<0.2)ship.raiseReactorTemperature();
		if(ship.getWaterPressure() == 0)
			if(Math.random()<0.6)ship.raiseReactorTemperature();
		if(ship.getWaterPressure() == 0)
			ship.lowerOxygenLevel();
		if(ship.getOxygenLevel() == 0)
			player.damage();
		if(Math.random()<0.1)ship.raiseThrust();
		
		if(--nextFlare < 1)flareDuration = (int)(Math.random()*3) +4;
		if((flareDuration-- > 0) && player.getRoom() != 4)player.damage();
		if(flareDuration ==0 )nextFlare =  (int)(Math.random()*8) +17;

		if(player.getCurrentHealth() < 1 || ship.getReactorTemperature() > 3)
		return -1;
		if(++time>100)return 1;
		return 0;
	}

	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("Time:");
		str.append(time);
		str.append("\n");
		str.append(player.toString());
		str.append(ship.toString());
		return str.toString();
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public SystemData getSystemData() {
		SystemData systemData = new SystemData();
		return systemData;
	}

	public OrbitalData getOrbitalData() {
		OrbitalData orbitalData = new OrbitalData();
		return orbitalData;
	}

	public List<Alert> getAlerts() {
		List<Alert> alerts = new ArrayList<>();
		return alerts;
	}

	public HealthData getHealthData() {
		HealthData healthData = new HealthData();
		return healthData;
	}
}
