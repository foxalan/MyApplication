package com.example.adanvace.info;

/**
 * Function : 联系人数据
 * Author : Alan
 * Modify Date : 9/8/17
 * Issue : TODO
 * Whether solve :
 */

public class ContactInfo {

    private boolean isDelete;
    private boolean isShow;
    private String name;
    private String number;

    public ContactInfo(boolean isDelete, boolean isShow, String name, String number) {
        this.isDelete = isDelete;
        this.isShow = isShow;
        this.name = name;
        this.number = number;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
