package com.sua.tavita.rateapp.tables;

/**
 * Created by Tavita on 29/06/2015.
 */
public class AppReview {
    public static final String TABLE = "appreview";
    public static final String ID = "_id";
    public static final String STARS = "stars";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String APPLICATION_ID = "aid";
    public static final String DEVICE_ID = "dev_id";
    public static final String TIMESTAMP = "t_stamp";



    public int aid;
    public int stars;
    public String title;
    public String description;
    public String deviceId;
    public String timeStamp;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }
}
