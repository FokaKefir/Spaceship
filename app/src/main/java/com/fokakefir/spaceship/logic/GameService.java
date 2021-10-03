package com.fokakefir.spaceship.logic;


import com.fokakefir.spaceship.gui.activity.MainActivity;
import com.fokakefir.spaceship.model.Alert;
import com.fokakefir.spaceship.model.HealthData;
import com.fokakefir.spaceship.model.OrbitalData;
import com.fokakefir.spaceship.model.Player;
import com.fokakefir.spaceship.model.Ship;
import com.fokakefir.spaceship.model.SystemData;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public class GameService {

    public static final int THRUST_RAISE = 1;
    public static final int THRUST_LOWER = 2;
    public static final int VOLTAGE_RAISE = 3;
    public static final int VOLTAGE_LOWER = 4;
    public static final int OXYGEN_LEVEL_RAISE = 5;
    public static final int OXYGEN_LEVEL_LOWER = 6;
    public static final int REACTOR_TEMPERATURE_RAISE = 7;
    public static final int REACTOR_TEMPERATURE_LOWER = 8;
    public static final int WATER_PRESSURE_RAISE = 9;
    public static final int WATER_PRESSURE_LOWER = 10;
    public static final int THRUSTER_MEND = 11;
    public static final int COMMUNICATION_MEND = 12;

    private int time;
    private int minutes;
    private int nextFlare;
    private int flareDuration;
    private Ship ship;
    private Player player;
    private int SAFE_DEVIATION;
    private HealthData lastHealthDataCheck;
    private List<Alert> alerts;

    public GameService() {
        time = 0;
        minutes = 0;
        ship = ship.getInstance();
        player = new Player(5);
        SAFE_DEVIATION = 100;
        nextFlare = (int) (Math.random() * 5) + 17;
        this.lastHealthDataCheck = new HealthData(player.getMaxHealth(), 0, 100, 100, 0);
        this.alerts = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    private void fire() {
        int r = (int) (Math.random() * 3);
        switch (r) {
            case 0:
                ship.lowerVoltage();
                alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "Warning", "The voltage has dropped."));
                break;
            case 1:
                ship.raiseWaterPressure();
                alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "Warning", "Water pressure increased. High water pressure may lead to the oxygen generator malfunctioning."));
                break;
            case 2:
                ship.lowerOxygenLevel();
                alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "Warning", "Water pressure increased. High water pressure may lead to the oxygen generator malfunctioning."));
                break;
        }
    }

    public int tick() {
        this.player.decreaseBoneDensity();
        this.player.decreaseMusclePower();

        if ((ship.getDeviation() < -SAFE_DEVIATION) || (ship.getDeviation() > SAFE_DEVIATION)) {
            if (Math.random() < 0.1) {
                ship.lowerWaterPressure();
                alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "Alert\t\t\t", "Water pressure increased due to an asteroid impact. High water pressure may lead to the oxygen generator malfunctioning."));
            }
            if (Math.random() < 0.1) {
                ship.lowerOxygenLevel();
                alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "Alert\t\t\t", "Oxygen levels increased due to an asteroid impact. High oxygen level may lead to fire hazard."));
            }
            if (Math.random() < 0.1) {
                player.damage();
                alerts.add(new Alert(player.getRoom(), getAlertTime(), "Alert\t\t\t", "You have been impacted by an asteroid."));
            }
            if (Math.random() < 0.1) {
                ship.breakCommunication();
                alerts.add(new Alert(Alert.ROOM_COMMANDER_ID, getAlertTime(), "Alert\t\t\t", "Communication is down due to an asteroid impact."));
            }
            if (Math.random() < 0.1) {
                ship.breakThruster();
                alerts.add(new Alert(Alert.ROOM_COMMANDER_ID, getAlertTime(), "Alert\t\t\t", "Thrusters are down due to an asteroid impact."));
            }
        }

        if (ship.getVoltage() == 3 && ship.getOxygenLevel() == 2)
            if (Math.random() < 0.1) fire();
        if (ship.getVoltage() == 3 && ship.getOxygenLevel() == 3)
            if (Math.random() < 0.4) fire();
        if (Math.random() < 0.05) fire();

        if (ship.getVoltage() == 1)
            if (Math.random() < 0.2) {
                alerts.add(new Alert(Alert.ROOM_LAB_ID, getAlertTime(), "Alert\t\t\t", "Water pressure dropped. There is not enough power to maintain the correct water pressure.Low water pressure may lead to reactor meltdown."));
                ship.lowerWaterPressure();
            }
        if (ship.getVoltage() == 1)
            if (Math.random() < 0.2) {
                alerts.add(new Alert(Alert.ROOM_LAB_ID, getAlertTime(), "Alert\t\t\t", "Oxygen levels dropped. There is not enough power to maintain the oxygen levels. Low oxygen levels may lead to suffocation."));
                ship.lowerOxygenLevel();
            }
        if (ship.getWaterPressure() == 3)
            if (Math.random() < 0.2) {
                alerts.add(new Alert(Alert.ROOM_LAB_ID, getAlertTime(), "Alert\t\t\t", "Oxygen levels dropped. The water pressure too high for the oxygen generator to maintain the correct levels.Low oxygen levels may lead to suffocation."));
                ship.lowerOxygenLevel();
            }
        if (ship.getWaterPressure() == 1)
            if (Math.random() < 0.2) {
                alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "Warning", "The reactor is overheating. The water pressure is too low to maintain the correct temperature."));
                ship.raiseReactorTemperature();
            }
        if (ship.getWaterPressure() == 0)
            if (Math.random() < 0.6) {
                alerts.add(new Alert(Alert.ROOM_TECH_ID, getAlertTime(), "Warning", "The reactor is overheating. The water pressure is too low to maintain the correct temperature."));
                ship.raiseReactorTemperature();
            }
        if (ship.getWaterPressure() == 0) {
            alerts.add(new Alert(Alert.ROOM_LAB_ID, getAlertTime(), "Warning", "Oxygen Levels dropped. There is no water pressure to maintain the oxygen levels. Low Oxygen levels may lead to suffocation."));
            ship.lowerOxygenLevel();
        }
        if (ship.getOxygenLevel() == 0) {
            alerts.add(new Alert(Alert.ROOM_LAB_ID, getAlertTime(), "Alert\t\t\t", "Critically low oxygen levels. Low Oxygen levels may lead to suffocation and other hardware failures."));
            player.damage();
        }
        if (Math.random() < 0.1) {
            alerts.add(new Alert(Alert.ROOM_COMMANDER_ID, getAlertTime(), "Warning", "You are leaving your optimal orbit. Getting into asteroid fields dramatically increases the chances of impacts."));
            ship.raiseThrust();
        }
        if (--nextFlare < 1) {
            flareDuration = (int) (Math.random() * 3) + 4;
            alerts.add(new Alert(Alert.ROOM_LAB_ID, getAlertTime(), "CRITICAL ALERT", "A solar flare has been detected! Please ingress the radiation shield!"));
            this.player.damageByRadioactivity();
        }
        if ((flareDuration-- > 0) && player.getRoom() != 2) player.damage();
        if (flareDuration == 0) nextFlare = (int) (Math.random() * 5) + 5;

        if (player.getCurrentHealth() < 1 || ship.getReactorTemperature() > 3)
            return -1;
        if (++time > 100) return 1;
        return 0;
    }

    public void heal() {
        if (time > player.getLastHealedTick())
            this.player.heal(time);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Time:");
        str.append(time);
        str.append("\n");
        str.append(player.toString());
        str.append(ship.toString());
        return str.toString();
    }

    public void makeNewHealthCheck() {
        this.lastHealthDataCheck = new HealthData(
                this.player.getCurrentHealth(),
                this.player.getPercentRadioactivity(),
                (int) this.player.getPercentMusclePower(),
                (int) this.player.getPercentBoneDensity(),
                time
        );
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
        SystemData systemData = new SystemData(
                this.ship.getCommunication(),
                (int)((((float)time) / (float) MainActivity.GAME_END_TICK) * 2000),
                this.ship.getThruster(),
                0,
                0,
                0,
                this.ship.getOxygenLevel() * 50,
                this.ship.getVoltage() * 50,
                this.ship.getWaterPressure() * 50,
                this.ship.getOxygenLevel() * 50,
                this.ship.getVoltage() * 50
        );
        return systemData;
    }

    public OrbitalData getOrbitalData() {
        OrbitalData orbitalData = new OrbitalData(
                (int)((((float)time) / (float) MainActivity.GAME_END_TICK) * 62000000.0),
                (int)((1.0 - (((float)time) / (float) MainActivity.GAME_END_TICK)) * 62000000.0),
                (int)(50000 + Math.random() * 10000),
                (int)(Math.random() * 10),
                MainActivity.GAME_END_TICK - time,
                (int)(Math.random() * 1000),
                (int)(((float)time * 100.0) / (float) MainActivity.GAME_END_TICK)
        );
        return orbitalData;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public HealthData getHealthData() {
        return this.lastHealthDataCheck;
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

    public void changeShipData(int type) {
        switch (type) {
            case THRUST_RAISE:
                this.ship.raiseThrust();
                break;
            case THRUST_LOWER:
                this.ship.lowerThrust();
                break;
            case VOLTAGE_RAISE:
                this.ship.raiseVoltage();
                break;
            case VOLTAGE_LOWER:
                this.ship.lowerVoltage();
                break;
            case OXYGEN_LEVEL_RAISE:
                this.ship.raiseOxygenLevel();
                break;
            case OXYGEN_LEVEL_LOWER:
                this.ship.lowerOxygenLevel();
                break;
            case REACTOR_TEMPERATURE_RAISE:
                this.ship.raiseReactorTemperature();
                break;
            case REACTOR_TEMPERATURE_LOWER:
                this.ship.lowerReactorTemperature();
                break;
            case WATER_PRESSURE_RAISE:
                this.ship.raiseWaterPressure();
                break;
            case WATER_PRESSURE_LOWER:
                this.ship.lowerWaterPressure();
                break;
            case THRUSTER_MEND:
                this.ship.mendThruster();
                break;
            case COMMUNICATION_MEND:
                this.ship.mendCommunication();
                break;
        }
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
