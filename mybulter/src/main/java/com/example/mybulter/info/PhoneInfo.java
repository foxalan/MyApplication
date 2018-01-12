package com.example.mybulter.info;

/**
 * Function Name :
 * Author : Alan
 * Modify Date : 8/8/17
 * Input Parameter :
 */

public class PhoneInfo {

    private String company;
    private String province;
    private String city;

    public PhoneInfo(String company, String province, String city) {
        this.company = company;
        this.province = province;
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
