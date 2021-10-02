package com.fokakefir.spaceship.model;

import java.util.concurrent.ThreadLocalRandom;

public class Alert {

    public static final int ROOM_COMMANDER_ID = 0;
    public static final int ROOM_MEDICAL_ID = 1;
    public static final int ROOM_LAB_ID = 2;
    public static final int ROOM_TECH_ID = 3;
    public static final int ROOM_AIRLOCK_ID = 4;

    private int roomId;
    private String code;
    private String date;
    private String type;
    private String problem;

    public Alert(int roomId, String date, String type, String problem) {
        this.roomId = roomId;
        this.code = String.valueOf(ThreadLocalRandom.current().nextInt(0, 9999 + 1));
        this.date = date;
        this.type = type;
        this.problem = problem;
    }

    public Alert(int roomId, String code, String date, String type, String problem) {
        this.roomId = roomId;
        this.code = code;
        this.date = date;
        this.type = type;
        this.problem = problem;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}
