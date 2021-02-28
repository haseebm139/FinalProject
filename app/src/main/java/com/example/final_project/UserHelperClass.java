package com.example.final_project;

public class UserHelperClass {




    public String username;
    public String email;
    public String phoneNumber;
    public String birthday;


    public UserHelperClass() {    }

    public UserHelperClass(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserHelperClass(String username) {
        this.username = username;
    }

    public UserHelperClass(String username, String email, String phoneNumber, String birthday) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
