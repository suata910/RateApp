package com.sua.tavita.rateapp.tables;

/**
 * Created by Tavita on 28/06/2015.
 */
public class Feature {
    public static final String TABLE = "feature";
    public static final String ID = "_id";
    public static final String FEATURE_NAME = "feature_name";

    public int id;
    public String feature_name;

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", feature_name='" + feature_name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(String feature_name) {
        this.feature_name = feature_name;
    }
}
