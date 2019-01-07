package com.agtechnosolution.leadsapplication;

/**
 * Created by AnujPc on 07-01-2019.
 */

public class Lead {

    private String name;
    private String email;
    private String mobile;
    private String comment;

    public Lead(String name, String email, String mobile, String comment) {
        this.name=name;
        this.email=email;
        this.mobile=mobile;
        this.comment=comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
