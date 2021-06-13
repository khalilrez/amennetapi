package com.supportportal.domain;

import java.util.Date;

public class OneTimePassword {
    private String code;
    private String date;

    public OneTimePassword() {
        this.date = new Date().toString();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
