package com.example.mybulter.info;

/**
 * Function :
 * Modify Date : 2018/1/17
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class UserBean {

    private String item;
    private String content;

    public UserBean(String item, String content) {
        this.item = item;
        this.content = content;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
