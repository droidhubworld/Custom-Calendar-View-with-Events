package com.inventivestack.customcalendarview.model;

import java.util.Date;

/**
 * Created by akumar1 on 11/22/2017.
 */

public class CalenderEventObjects {
    private int id;
    private String title;
    private String message;
    private Date date;

    public CalenderEventObjects() {
    }

    public CalenderEventObjects(String title,String message, Date date) {
        this.title = title;
        this.message = message;
        this.date = date;
    }
    public CalenderEventObjects(int id, String message, Date date) {
        this.date = date;
        this.message = message;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CalenderEventObjects{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}