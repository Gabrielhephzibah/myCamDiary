package com.enyata.camdiary.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "pds_data")
public class PdsDataCollection implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("farmer_id")
    @ColumnInfo(name = "farmer_id")
    public String farmerId;

    @SerializedName("first_name")
    @ColumnInfo(name = "first_name")
    public String firstName;

    @SerializedName("last_name")
    @ColumnInfo(name = "last_name")
    public String lastName;

    @SerializedName("gender")
    @ColumnInfo(name = "gender")
    public String gender;

    @SerializedName("phone_no")
    @ColumnInfo(name = "phone_no")
    public String phone_no;

    @SerializedName("sources_of_income")
    @ColumnInfo(name = "sources_of_income")
    public String sourcesOfIncome;

    @SerializedName("main_income")
    @ColumnInfo(name = "main_income")
    public String mainIncome;

    @SerializedName("weekly_earning")
    @ColumnInfo(name = "weekly_earning")
    public String weekEarning;

    @SerializedName("monthly_earning")
    @ColumnInfo(name = "monthly_earning")
    public String monthlyEarning;

    @SerializedName("market_days")
    @ColumnInfo(name = "market_days")
    public String marketDays;


    @SerializedName("litres_of_milk_per_day")
    @ColumnInfo(name = "litres_of_milk_per_day")
    public String litresOfMilkPerDay;

    @SerializedName("house_hold_consumption")
    @ColumnInfo(name = "house_hold_consumption")
    public String houseHoldConsumption;

    @SerializedName("milk_for_sale")
    @ColumnInfo(name = "milk_for_sale")
    public String milkForSale;

    @SerializedName("milk_challenges")
    @ColumnInfo(name = "milk_challenges")
    public String milkChallenges;

    @SerializedName("cow_in_Abuja")
    @ColumnInfo(name = "cow_in_Abuja")
    public String cowInAbuja;

    @SerializedName("total_cow")
    @ColumnInfo(name = "total_cow")
    public String totalCow;

    @SerializedName("milking_cow")
    @ColumnInfo(name = "milking_cow")
    public String milkingCow;

    @SerializedName("recommendations")
    @ColumnInfo(name = "recommendations")
    public String recommendations;

    @SerializedName("feedback")
    @ColumnInfo(name = "feedback")
    public String feedback;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getMainIncome() {
        return mainIncome;
    }

    public void setMainIncome(String mainIncome) {
        this.mainIncome = mainIncome;
    }

    public String getSourcesOfIncome() {
        return sourcesOfIncome;
    }

    public void setSourcesOfIncome(String sourcesOfIncome) {
        this.sourcesOfIncome = sourcesOfIncome;
    }

    public String getWeekEarning() {
        return weekEarning;
    }

    public void setWeekEarning(String weekEarning) {
        this.weekEarning = weekEarning;
    }

    public String getMonthlyEarning() {
        return monthlyEarning;
    }

    public void setMonthlyEarning(String monthlyEarning) {
        this.monthlyEarning = monthlyEarning;
    }

    public String getMarketDays() {
        return marketDays;
    }

    public void setMarketDays(String marketDays) {
        this.marketDays = marketDays;
    }

    public String getLitresOfMilkPerDay() {
        return litresOfMilkPerDay;
    }

    public void setLitresOfMilkPerDay(String litresOfMilkPerDay) {
        this.litresOfMilkPerDay = litresOfMilkPerDay;
    }

    public String getHouseHoldConsumption() {
        return houseHoldConsumption;
    }

    public void setHouseHoldConsumption(String houseHoldConsumption) {
        this.houseHoldConsumption = houseHoldConsumption;
    }

    public String getMilkForSale() {
        return milkForSale;
    }

    public void setMilkForSale(String milkForSale) {
        this.milkForSale = milkForSale;
    }

    public String getMilkChallenges() {
        return milkChallenges;
    }

    public void setMilkChallenges(String milkChallenges) {
        this.milkChallenges = milkChallenges;
    }

    public String getCowInAbuja() {
        return cowInAbuja;
    }

    public void setCowInAbuja(String cowInAbuja) {
        this.cowInAbuja = cowInAbuja;
    }

    public String getTotalCow() {
        return totalCow;
    }

    public void setTotalCow(String totalCow) {
        this.totalCow = totalCow;
    }

    public String getMilkingCow() {
        return milkingCow;
    }

    public void setMilkingCow(String milkingCow) {
        this.milkingCow = milkingCow;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "PdsDataCollection{" +
                "id=" + id +
                ", farmerId='" + farmerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", income='" + mainIncome + '\'' +
                ", sourcesOfIncome='" + sourcesOfIncome + '\'' +
                ", weekEarning='" + weekEarning + '\'' +
                ", monthlyEarning='" + monthlyEarning + '\'' +
                ", marketDays='" + marketDays + '\'' +
                ", litresOfMilkPerDay='" + litresOfMilkPerDay + '\'' +
                ", milkConsumption='" + houseHoldConsumption + '\'' +
                ", milkForSale='" + milkForSale + '\'' +
                ", milkChallenges='" + milkChallenges + '\'' +
                ", cowInAbuja='" + cowInAbuja + '\'' +
                ", totalCow='" + totalCow + '\'' +
                ", milkingCow='" + milkingCow + '\'' +
                ", recommendations='" + recommendations + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
