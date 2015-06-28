package com.sua.tavita.rateapp.tables;

/**
 * Created by Tavita on 28/06/2015.
 */
public class App {
    public static final String TABLE = "app";
    public static final String ID = "_id";
    public static final String NAME = "name";

    public int id;
    public String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
