package com.sua.tavita.rateapp.tables;

/**
 * Created by Tavita on 28/06/2015.
 */
public class Defect {
    public static final String TABLE = "defect";
    public static final String ID = "_id";
    public static final String AID = "aid";
    public static final String FID = "fid";
    public static final String IID = "iid";
    public static final String COUNTER = "counter";


    public int id;
    public int aid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int fid;
    public int iid;
    public int counter;

}
