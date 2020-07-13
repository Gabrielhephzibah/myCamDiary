package com.enyata.camdiary.ui.aggregations.collectorCollection;

public class ChurnDetailsItem {
    private String firstName;
    private String lastName;
    private String verificationId;
    private String volume;

    public ChurnDetailsItem(String firstName, String lastName, String verificationId, String volume) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.verificationId = verificationId;
        this.volume = volume;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "ChurnDetailsItem{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", verificationId='" + verificationId + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}
