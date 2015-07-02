package com.sua.tavita.rateapp.tables;

/**
 * Created by Teuila on 19/06/15.
 */
public class User {
    public static final String TABLE = "user";
    public static final String DEVICE_ID = "device_id";
    public static final String TIMESTAMP = "timestamp";

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String device_id;
    public String timeStamp;
}
