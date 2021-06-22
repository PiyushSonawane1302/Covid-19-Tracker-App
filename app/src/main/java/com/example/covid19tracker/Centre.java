package com.example.covid19tracker;

public class Centre {

    String centerName;
    String centerAddress;
    String centerFromTiming;
    String centerToTiming;
    String fees;
    String vaccineName;
    String availability;
    int ageLimit;

    public Centre(String centerName, String centerAddress, String centerFromTiming, String centerToTiming, String fees, String vaccineName, String availability, int ageLimit) {
        this.centerName = centerName;
        this.centerAddress = centerAddress;
        this.centerFromTiming = centerFromTiming;
        this.centerToTiming = centerToTiming;
        this.fees = fees;
        this.vaccineName = vaccineName;
        this.availability = availability;
        this.ageLimit = ageLimit;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getCenterFromTiming() {
        return centerFromTiming;
    }

    public void setCenterFromTiming(String centerFromTiming) {
        this.centerFromTiming = centerFromTiming;
    }

    public String getCenterToTiming() {
        return centerToTiming;
    }

    public void setCenterToTiming(String centerToTiming) {
        this.centerToTiming = centerToTiming;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }
}

