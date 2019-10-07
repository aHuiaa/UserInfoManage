package com.pc.domain;

public class User {
    int u_id;
    String u_nikename;
    String u_gender;
    int u_age;
    String u_address;
    String u_qq;
    String u_email;
    String u_username;
    String u_password;
    public User() {
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_nikename() {
        return u_nikename;
    }

    public void setU_nikename(String u_nikename) {
        this.u_nikename = u_nikename;
    }

    public String getU_gender() {
        return u_gender;
    }

    public void setU_gender(String u_gender) {
        this.u_gender = u_gender;
    }

    public int getU_age() {
        return u_age;
    }

    public void setU_age(int u_age) {
        this.u_age = u_age;
    }

    public String getU_address() {
        return u_address;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public String getU_qq() {
        return u_qq;
    }

    public void setU_qq(String u_qq) {
        this.u_qq = u_qq;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_username() {
        return u_username;
    }

    public void setU_username(String u_username) {
        this.u_username = u_username;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id=" + u_id +
                ", u_nikename='" + u_nikename + '\'' +
                ", u_gender='" + u_gender + '\'' +
                ", u_age=" + u_age +
                ", u_address='" + u_address + '\'' +
                ", u_qq='" + u_qq + '\'' +
                ", u_email='" + u_email + '\'' +
                ", u_username='" + u_username + '\'' +
                ", u_password='" + u_password + '\'' +
                '}';
    }
}
