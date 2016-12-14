package com.again.fitbox.fitbox_android.model;

/**
 * Created by jeong on 2016. 12. 5..
 */

public class Board {

    private int id;
    private String date;
    private String inDate;
    private String moDate;
    private String delete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }


    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getMoDate() {
        return moDate;
    }

    public void setMoDate(String moDate) {
        this.moDate = moDate;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", inDate='" + inDate + '\'' +
                ", moDate='" + moDate + '\'' +
                ", delete='" + delete + '\'' +
                '}';
    }
}
