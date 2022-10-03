package com.example.middleprojectapplication;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class Contact implements Serializable {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String homeAddress;
    private String webAddress;
    private String birthday;
    private String timeToCall;
    private ArrayList<String> bestDays;
    private Bitmap bitmap;

    public Contact(String fullName, String phoneNumber, String email, String homeAddress, String webAddress, String birthday, String timeToCall, ArrayList<String> bestDays, Bitmap bitmap) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.homeAddress = homeAddress;
        this.webAddress = webAddress;
        this.birthday = birthday;
        this.timeToCall = timeToCall;
        this.bestDays = bestDays;
        this.bitmap = bitmap;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTimeToCall() {
        return timeToCall;
    }

    public void setTimeToCall(String timeToCall) {
        this.timeToCall = timeToCall;
    }

    public ArrayList<String> getBestDays() {
        return bestDays;
    }

    public void setBestDays(ArrayList<String> bestDays) {
        this.bestDays = bestDays;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


}
