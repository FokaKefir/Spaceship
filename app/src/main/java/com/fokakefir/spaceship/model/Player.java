package com.fokakefir.spaceship.model;

import com.fokakefir.spaceship.gui.activity.MainActivity;

public class Player{
	private int maxHealth;
	private int currentHealth;
	private int room;
	private int lastHealedTick;
	private boolean movable;

	private int percentRadioactivity;
	private float percentMusclePower;
	private float percentBoneDensity;

	public Player(int maxHealth){
		this.room = 0;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		this.movable = true;
		this.lastHealedTick = 0;
		this.percentRadioactivity = 0;
		this.percentMusclePower = 100;
		this.percentBoneDensity = 100;
	}

	public int getRoom(){
		return room;
	}

	public int getCurrentHealth(){
		return currentHealth;
	}

	public int heal(int tick){
		if(currentHealth == maxHealth)return 0;
		currentHealth++;
		lastHealedTick = tick;
		return 1;
	}

	public boolean damage(){
		return((--currentHealth) == 0);
	}

	public void damageByRadioactivity() {
		if (movable)
			this.percentRadioactivity += 1;
	}

	public void decreaseBoneDensity() {
		this.percentBoneDensity -= (60.0 / (float)MainActivity.GAME_END_TICK);
	}

	public void decreaseMusclePower() {
		this.percentMusclePower -= (60.0 / (float)MainActivity.GAME_END_TICK);
	}

	public void moveLeft(){
		if(room!=0)
			room--;
	}

	public void moveRight(){
		if(room!=5)
			room++;
	}

	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("[");
		str.append("\nHealth:");
		str.append(currentHealth);
		str.append("\nRoom:");
		str.append(room);
		str.append("\n]");
		return str.toString();
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public int getLastHealedTick() {
		return lastHealedTick;
	}

	public void setLastHealedTick(int lastHealedTick) {
		this.lastHealedTick = lastHealedTick;
	}

	public int getPercentRadioactivity() {
		return percentRadioactivity;
	}

	public float getPercentMusclePower() {
		return percentMusclePower;
	}

	public float getPercentBoneDensity() {
		return percentBoneDensity;
	}
}
