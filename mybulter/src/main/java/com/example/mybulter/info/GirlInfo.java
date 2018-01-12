package com.example.mybulter.info;

/**
 * Function :相册的信息
 * Author : Alan
 * Modify Date : 15/8/17
 * Issue : TODO
 * Whether solve :
 */

public class GirlInfo {

    private String name;
    private String imageUrl;

    public GirlInfo(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
