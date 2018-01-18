package com.example.adanvace.info;

import android.graphics.Rect;

/**
 * Created by Alan on 2017/11/29.
 */

public class DayInfo {

    private String name;
    private int position;
    private Rect rect;
    private int status;

    public DayInfo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DayInfo(String name, int position, Rect rect, int status) {
        this.name = name;
        this.position = position;
        this.rect = rect;
        this.status = status;
    }

    @Override
    public String toString() {
        return "DayInfo{" +
                "status=" + status +
                '}';
    }
}
