package com.fokakefir.spaceship.model;

public class HealthData {

    private int health;
    private int percentRadioactivity;
    private int percentMusclePower;
    private int percentBoneDensity;
    private int lastTick;

    public HealthData(int health, int percentRadioactivity, int percentMusclePower, int percentBoneDensity, int lastTick) {
        this.health = health;
        this.percentRadioactivity = percentRadioactivity;
        this.percentMusclePower = percentMusclePower;
        this.percentBoneDensity = percentBoneDensity;
        this.lastTick = lastTick;
    }

    public HealthData() {
        this.health = 1;
        this.percentRadioactivity = 0;
        this.percentMusclePower = 0;
        this.percentBoneDensity = 0;
        this.lastTick = 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPercentRadioactivity() {
        return percentRadioactivity;
    }

    public void setPercentRadioactivity(int percentRadioactivity) {
        this.percentRadioactivity = percentRadioactivity;
    }

    public int getPercentMusclePower() {
        return percentMusclePower;
    }

    public void setPercentMusclePower(int percentMusclePower) {
        this.percentMusclePower = percentMusclePower;
    }

    public int getPercentBoneDensity() {
        return percentBoneDensity;
    }

    public void setPercentBoneDensity(int percentBoneDensity) {
        this.percentBoneDensity = percentBoneDensity;
    }

    public int getLastTick() {
        return lastTick;
    }

    public void setLastTick(int lastTick) {
        this.lastTick = lastTick;
    }
}
