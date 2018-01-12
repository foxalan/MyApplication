package com.example.mybulter.info;

/**
 * Function Name :
 * Author : Alan
 * Modify Date : 8/8/17
 * Input Parameter :
 */

public class ChoiceInfo {

    private String title;
    private String pub;
    private String imageUrl;


    public ChoiceInfo(String title, String pub, String imageUrl) {
        this.title = title;
        this.pub = pub;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ChoiceInfo{" +
                "title='" + title + '\'' +
                ", pub='" + pub + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
