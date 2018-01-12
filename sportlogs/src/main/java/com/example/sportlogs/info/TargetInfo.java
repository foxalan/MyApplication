package com.example.sportlogs.info;

import android.graphics.Paint;

/**
 * Function :
 * Author : Alan
 * Modify Date : 31/8/17
 * Issue : TODO
 * Whether solve :
 */

public class TargetInfo {

    private int id;
    private String journalName;
    private String targetName;
    private int targetValue;
    private String startTime;
    private String endTime;
    private boolean isFinish;

    public int getFinishValue() {
        return finishValue;
    }

    public void setFinishValue(int finishValue) {
        this.finishValue = finishValue;
    }

    private boolean isFail;
    private int finishValue;


    public TargetInfo(String journalName, String targetName, int targetValue, String startTime, String endTime, boolean isFinish, boolean isFail,int finishValue) {
        this.journalName = journalName;
        this.targetName = targetName;
        this.targetValue = targetValue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isFinish = isFinish;
        this.isFail = isFail;
        this.finishValue = finishValue;
    }

    @Override
    public String toString() {
        return "TargetInfo{" +
                "id=" + id +
                ", targetName='" + targetName + '\'' +
                ", targetValue=" + targetValue +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", isFinish=" + isFinish +
                ", isFail=" + isFail +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public boolean isFail() {
        return isFail;
    }

    public void setFail(boolean fail) {
        isFail = fail;
    }
}
