package com.fokakefir.spaceship.model;

public class Player{
	private int maxHealth;
	private int currentHealth;
	private int room;

	public Player(int maxHealth){
		this.room = 0;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
	}

	public int getRoom(){
		return room;
	}

	public int getCurrentHealth(){
		return currentHealth;
	}

	public int heal(){
		if(currentHealth == maxHealth)return 0;
		currentHealth++;
		return 1;
	}

	public boolean damage(){
		return((--currentHealth) == 0);
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
}
