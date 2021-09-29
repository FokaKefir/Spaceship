package com.fokakefir.spaceship.logic;


import com.fokakefir.spaceship.model.Player;
import com.fokakefir.spaceship.model.Ship;

import java.lang.StringBuilder;

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
}
