package com.example.sportlogs.info;

/**
 * Function :
 * Author : Alan
 * Modify Date : 30/8/17
 * Issue : TODO
 * Whether solve :
 */

public class JournalInfo {

    private String journal_name;
    private String journal_time;
    private int journal_values;

    public JournalInfo() {

    }

    @Override
    public String toString() {
        return "JournalInfo{" +
                "journal_name='" + journal_name + '\'' +
                ", journal_time='" + journal_time + '\'' +
                ", journal_values=" + journal_values +
                '}';
    }

    public JournalInfo(String journal_name, String journal_time, int journal_values) {
        this.journal_name = journal_name;
        this.journal_time = journal_time;
        this.journal_values = journal_values;
    }

    public String getJournal_name() {
        return journal_name;
    }

    public void setJournal_name(String journal_name) {
        this.journal_name = journal_name;
    }

    public String getJournal_time() {
        return journal_time;
    }

    public void setJournal_time(String journal_time) {
        this.journal_time = journal_time;
    }

    public int getJournal_values() {
        return journal_values;
    }

    public void setJournal_values(int journal_values) {
        this.journal_values = journal_values;
    }
}
