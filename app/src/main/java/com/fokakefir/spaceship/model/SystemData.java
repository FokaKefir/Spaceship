package com.fokakefir.spaceship.model;

public class SystemData {

    private boolean communicationStatus;
    private int communicationDelay;
    private boolean engineStatus;
    private int engineTemperature;
    private int engineThrust;
    private int engineFuel;
    private int healthOxygenPercent;
    private int healthTensionPercent;
    private int waterPressurePercent;
    private int reactorOxygenPercent;
    private int reactorTensionPercent;

    public SystemData(boolean communicationStatus, int communicationDelay, boolean engineStatus, int engineTemperature, int engineThrust, int engineFuel, int healthOxygenPercent, int healthTensionPercent, int waterPressurePercent, int reactorOxygenPercent, int reactorTensionPercent) {
        this.communicationStatus = communicationStatus;
        this.communicationDelay = communicationDelay;
        this.engineStatus = engineStatus;
        this.engineTemperature = engineTemperature;
        this.engineThrust = engineThrust;
        this.engineFuel = engineFuel;
        this.healthOxygenPercent = healthOxygenPercent;
        this.healthTensionPercent = healthTensionPercent;
        this.waterPressurePercent = waterPressurePercent;
        this.reactorOxygenPercent = reactorOxygenPercent;
        this.reactorTensionPercent = reactorTensionPercent;
    }

    public SystemData() {
        this.communicationStatus = true;
        this.communicationDelay = 0;
        this.engineStatus = true;
        this.engineTemperature = 0;
        this.engineThrust = 0;
        this.engineFuel = 0;
        this.healthOxygenPercent = 0;
        this.healthTensionPercent = 0;
        this.waterPressurePercent = 0;
        this.reactorOxygenPercent = 0;
        this.reactorTensionPercent = 0;
    }

    public boolean isCommunicationStatus() {
        return communicationStatus;
    }

    public void setCommunicationStatus(boolean communicationStatus) {
        this.communicationStatus = communicationStatus;
    }

    public int getCommunicationDelay() {
        return communicationDelay;
    }

    public void setCommunicationDelay(int communicationDelay) {
        this.communicationDelay = communicationDelay;
    }

    public boolean isEngineStatus() {
        return engineStatus;
    }

    public void setEngineStatus(boolean engineStatus) {
        this.engineStatus = engineStatus;
    }

    public int getEngineTemperature() {
        return engineTemperature;
    }

    public void setEngineTemperature(int engineTemperature) {
        this.engineTemperature = engineTemperature;
    }

    public int getEngineThrust() {
        return engineThrust;
    }

    public void setEngineThrust(int engineThrust) {
        this.engineThrust = engineThrust;
    }

    public int getEngineFuel() {
        return engineFuel;
    }

    public void setEngineFuel(int engineFuel) {
        this.engineFuel = engineFuel;
    }

    public int getHealthOxygenPercent() {
        return healthOxygenPercent;
    }

    public void setHealthOxygenPercent(int healthOxygenPercent) {
        this.healthOxygenPercent = healthOxygenPercent;
    }

    public int getHealthTensionPercent() {
        return healthTensionPercent;
    }

    public void setHealthTensionPercent(int healthTensionPercent) {
        this.healthTensionPercent = healthTensionPercent;
    }

    public int getWaterPressurePercent() {
        return waterPressurePercent;
    }

    public void setWaterPressurePercent(int waterPressurePercent) {
        this.waterPressurePercent = waterPressurePercent;
    }

    public int getReactorOxygenPercent() {
        return reactorOxygenPercent;
    }

    public void setReactorOxygenPercent(int reactorOxygenPercent) {
        this.reactorOxygenPercent = reactorOxygenPercent;
    }

    public int getReactorTensionPercent() {
        return reactorTensionPercent;
    }

    public void setReactorTensionPercent(int reactorTensionPercent) {
        this.reactorTensionPercent = reactorTensionPercent;
    }
}
