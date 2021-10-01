package com.fokakefir.spaceship.model;

public class OrbitalData {

    private int traveledDistance;
    private int distance;
    private int speed;
    private int acceleration;
    private int tickRemaining;
    private int trackDeviation;
    private int distancePercent;

    public OrbitalData(int traveledDistance, int distance, int speed, int acceleration, int tickRemaining, int trackDeviation, int distancePercent) {
        this.traveledDistance = traveledDistance;
        this.distance = distance;
        this.speed = speed;
        this.acceleration = acceleration;
        this.tickRemaining = tickRemaining;
        this.trackDeviation = trackDeviation;
        this.distancePercent = distancePercent;
    }

    public OrbitalData() {
        this.traveledDistance = 0;
        this.distance = 0;
        this.speed = 0;
        this.acceleration = 0;
        this.tickRemaining = 0;
        this.trackDeviation = 0;
        this.distancePercent = 0;
    }

    public int getTraveledDistance() {
        return traveledDistance;
    }

    public void setTraveledDistance(int traveledDistance) {
        this.traveledDistance = traveledDistance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getTickRemaining() {
        return tickRemaining;
    }

    public void setTickRemaining(int tickRemaining) {
        this.tickRemaining = tickRemaining;
    }

    public int getTrackDeviation() {
        return trackDeviation;
    }

    public void setTrackDeviation(int trackDeviation) {
        this.trackDeviation = trackDeviation;
    }

    public int getDistancePercent() {
        return distancePercent;
    }

    public void setDistancePercent(int distancePercent) {
        this.distancePercent = distancePercent;
    }
}
