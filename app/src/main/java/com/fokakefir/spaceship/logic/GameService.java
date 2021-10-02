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
	private List<Alert> alerts;

	public GameService(){
		time = 0;
		minutes = 0;
		ship = ship.getInstance();
		player = new Player(4);
		SAFE_DEVIATION = 100;
		nextFlare = (int)(Math.random()*5) + 17;
		this.alerts = new ArrayList<>();
	}

	public Player getPlayer(){
		return player;
	}

	private void fire(){
		int r = (int)(Math.random()*3);
		switch(r){
			case 0:
				ship.lowerVoltage();
				alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "warning", "Voltage has dropped."));
				break;
			case 1:
				ship.raiseWaterPressure();
				alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "warning", "Water Pressure has risen.High water pressure may lead to oxygen generator malfunction"));
				break;
			case 2:
				ship.lowerOxygenLevel();
				alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "warning", "Water Pressure has risen.High water pressure may lead to oxygen generator malfunction"));
				break;
		}
	}

	public int tick(){
		if((ship.getDeviation()<-SAFE_DEVIATION) || (ship.getDeviation()>SAFE_DEVIATION)){
			if(Math.random()<0.1){
				ship.lowerWaterPressure();
				alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "warning", "Water Pressure has risen due to an asteroid collision.High water pressure may lead to oxygen generator malfunction"));
			}
			if(Math.random()<0.1){
				ship.lowerOxygenLevel();
				alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "warning", "Oxygen Level has risen due to an asteroid collision.High oxygen level may lead to fire hazard"));
			}
			if(Math.random()<0.1){
				player.damage();
				alerts.add(new Alert(player.getRoom(), getAlertTime(), "warning", "You have been damaged due to an asteroid collision"));
			}
			if(Math.random()<0.1){
				ship.breakCommunication();
				alerts.add(new Alert(Alert.ROOM_COMMANDER_ID, getAlertTime(), "warning", "Communication have been broken due to an asteroid collision"));
			}
			if(Math.random()<0.1){
				ship.breakThruster();
				alerts.add(new Alert(Alert.ROOM_COMMANDER_ID, getAlertTime(), "warning", "Thrusters has been broken due to an asteroid collision"));
			}
		}

		if(ship.getVoltage()==3 && ship.getOxygenLevel() == 2)
			if(Math.random()<0.1)fire();
		if(ship.getVoltage()==3 && ship.getOxygenLevel() == 3)
			if(Math.random()<0.4)fire();
		if(Math.random()<0.05)fire();

		if(ship.getVoltage() == 1)
			if(Math.random()<0.2)
			{
				alerts.add(new Alert(Alert.ROOM_LAB_ID, getAlertTime(), "warning", "Water Pressure has fallen.Not enough power to maintain water pressure. Low water pressure may lead to reactor overheating"));
				ship.lowerWaterPressure();
			}
		if(ship.getVoltage() == 1)
			if(Math.random()<0.2)
			{
				alerts.add(new Alert(Alert.ROOM_LAB_ID, getAlertTime(), "warning", "Oxygen Level has fallen.Not enough power to maintain oxygen level. Low Oxygen levels may lead to suffocation."));
				ship.lowerOxygenLevel();
			}
		if(ship.getWaterPressure() == 3)
			if(Math.random()<0.2)
			{
				alerts.add(new Alert(Alert.ROOM_LAB_ID, getAlertTime(), "warning", "Oxygen Level has fallen.Water pressure too high for the Oxygen Generator to maintain oxygen level.Low Oxygen levels may lead to suffocation."));
				ship.lowerOxygenLevel();
			}
		if(ship.getWaterPressure() == 1)
			if(Math.random()<0.2){
				alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "warning", "Reactor is overheating.Water pressure is too low, to cool reactor"));
				ship.raiseReactorTemperature();
			}
		if(ship.getWaterPressure() == 0)
			if(Math.random()<0.6){
				alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "warning", "Reactor is overheating.Water pressure is too low, to cool reactor"));
				ship.raiseReactorTemperature();
			}
		if(ship.getWaterPressure() == 0){
			alerts.add(new Alert(Alert.ROOM_LAB_ID, getAlertTime(), "warning", "Oxygen Level has fallen.There is no water for the Oxygen Generator to maintain oxygen level.Low Oxygen levels may lead to suffocation."));
			ship.lowerOxygenLevel();
		}
		if(ship.getOxygenLevel() == 0)
		{
			alerts.add(new Alert(Alert.ROOM_LAB_ID, getAlertTime(), "warning", "Oxygen Level is critically low .Low Oxygen levels may lead to suffocation. For more information please contact the Command Module :)"));
			player.damage();
		}
		if(Math.random()<0.1)
		{
			alerts.add(new Alert(Alert.ROOM_COMMANDER_ID, getAlertTime(), "warning", "You might be leaving your optimal orbit. Getting into an asteroid field dramatically raises the chance of collisions"));
			ship.raiseThrust();
		}
		if(--nextFlare < 1)
		{
			flareDuration = (int)(Math.random()*3) +4;
			alerts.add(new Alert(Alert.ROOM_LAB_ID, getAlertTime(), "warning", "Solar flare Incoming"));
		}
		if((flareDuration-- > 0) && player.getRoom() != 2 && !player.isMovable())player.damage();
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
		return alerts;
	}

	public HealthData getHealthData() {
		HealthData healthData = new HealthData();
		return healthData;
	}

	public void setPlayerRoom(int room) {
		this.player.setRoom(room);
	}

	public void setPlayerMovable(boolean movable) {
		this.player.setMovable(movable);
	}

	public boolean isPlayerMovable() {
		return this.player.isMovable();
	}

	private String getAlertTime() {
		String strHour;
		String strMin;
		int hour = minutes / 60;
		int min = minutes % 60;

		if (hour < 10)
			strHour = "0" + hour;
		else
			strHour = hour + "";

		if (min < 10)
			strMin = "0" + min;
		else
			strMin = min + "";

		return strHour + ":" + strMin;
	}
}
