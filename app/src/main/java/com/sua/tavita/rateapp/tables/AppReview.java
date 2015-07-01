package com.sua.tavita.rateapp.tables;

/**
 * Created by Tavita on 29/06/2015.
 */
public class AppReview {
    public static final String TABLE = "appreview";
    public static final String ID = "_id";
    public static final String STARS = "stars";
    public static final String COMMENT = "comment";
    public static final String APPLICATION_ID = "aid";

    public int id;
    public int aid;
    public int stars;
    public String comment;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
