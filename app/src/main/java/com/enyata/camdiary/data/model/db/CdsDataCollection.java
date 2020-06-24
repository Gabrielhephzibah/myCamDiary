package com.enyata.camdiary.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
@Entity(tableName = "cds_data")
public class CdsDataCollection implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("first_name")
    @ColumnInfo(name = "first_name")
    public String firstName;

    @SerializedName("last_name")
    @ColumnInfo(name = "last_name")
    public String lastName;

    @SerializedName("gender")
    @ColumnInfo(name = "gender")
    public String gender;

    @SerializedName("age")
    @ColumnInfo(name = "age")
    public String age;

    @SerializedName("marital_status")
    @ColumnInfo(name = "marital_status")
    public String marital_status;

    @SerializedName("phone_no")
    @ColumnInfo(name = "phone_no")
    public String phone_no;

    @SerializedName("adult_18_above")
    @ColumnInfo(name = "adult_18_above")
    public String adult18Above;

    @SerializedName("electoral_ward")
    @ColumnInfo(name = "electoral_ward")
    public String electoralWard;

    @SerializedName("area_council")
    @ColumnInfo(name = "area_council")
    public String areaCouncil;

    @SerializedName("community_name")
    @ColumnInfo(name = "community_name")
    public String communityName;

    @SerializedName("income")
    @ColumnInfo(name = "income")
    public String income;

    @SerializedName("sources_of_income")
    @ColumnInfo(name = "sources_of_income")
    public String sourcesOfIncome;

    @SerializedName("weekly_earning")
    @ColumnInfo(name = "weekly_earning")
    public String weekEarning;

    @SerializedName("monthly_earning")
    @ColumnInfo(name = "monthly_earning")
    public String monthlyEarning;

    @SerializedName("litres_of_milk_per_day")
    @ColumnInfo(name = "litres_of_milk_per_day")
    public String litresOfMilkPerDay;


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

    @SerializedName("feedback")
    @ColumnInfo(name = "feedback")
    public String feedback;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAdult18Above() {
        return adult18Above;
    }

    public void setAdult18Above(String adult18Above) {
        this.adult18Above = adult18Above;
    }

    public String getElectoralWard() {
        return electoralWard;
    }

    public void setElectoralWard(String electoralWard) {
        this.electoralWard = electoralWard;
    }

    public String getAreaCouncil() {
        return areaCouncil;
    }

    public void setAreaCouncil(String areaCouncil) {
        this.areaCouncil = areaCouncil;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
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

    public String getLitresOfMilkPerDay() {
        return litresOfMilkPerDay;
    }

    public void setLitresOfMilkPerDay(String litresOfMilkPerDay) {
        this.litresOfMilkPerDay = litresOfMilkPerDay;
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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "CdsDataCollection{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", marital_status='" + marital_status + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", adult18Above='" + adult18Above + '\'' +
                ", electoralWard='" + electoralWard + '\'' +
                ", areaCouncil='" + areaCouncil + '\'' +
                ", communityName='" + communityName + '\'' +
                ", income='" + income + '\'' +
                ", sourcesOfIncome='" + sourcesOfIncome + '\'' +
                ", weekEarning='" + weekEarning + '\'' +
                ", monthlyEarning='" + monthlyEarning + '\'' +
                ", litresOfMilkPerDay='" + litresOfMilkPerDay + '\'' +
                ", milkForSale='" + milkForSale + '\'' +
                ", milkChallenges='" + milkChallenges + '\'' +
                ", cowInAbuja='" + cowInAbuja + '\'' +
                ", totalCow='" + totalCow + '\'' +
                ", milkingCow='" + milkingCow + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
