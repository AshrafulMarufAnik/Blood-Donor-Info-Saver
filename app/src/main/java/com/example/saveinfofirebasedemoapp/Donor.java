package com.example.saveinfofirebasedemoapp;

public class Donor {
    private String donorName,donorPhone,donorLocation,donorEmail,donorBloodGroup;
    private String uid,donorId;

    public Donor() {
    }

    public Donor(String donorName, String donorPhone, String donorLocation, String donorEmail, String donorBloodGroup) {
        this.donorName = donorName;
        this.donorPhone = donorPhone;
        this.donorLocation = donorLocation;
        this.donorEmail = donorEmail;
        this.donorBloodGroup = donorBloodGroup;
    }

    public Donor(String donorName, String donorPhone, String donorLocation, String donorEmail, String donorBloodGroup, String donorId) {
        this.donorName = donorName;
        this.donorPhone = donorPhone;
        this.donorLocation = donorLocation;
        this.donorEmail = donorEmail;
        this.donorBloodGroup = donorBloodGroup;
        this.donorId = donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getDonorPhone() {
        return donorPhone;
    }

    public String getDonorLocation() {
        return donorLocation;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public String getDonorBloodGroup() {
        return donorBloodGroup;
    }

    public String getUid() {
        return uid;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }
}
