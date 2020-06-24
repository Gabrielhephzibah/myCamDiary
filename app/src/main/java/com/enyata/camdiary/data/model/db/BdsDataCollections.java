package com.enyata.camdiary.data.model.db;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "bds_data")
public class BdsDataCollections implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("image_url")
    @ColumnInfo(name = "image_url")
    public String imageUrl;

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

    @SerializedName("family_name")
    @ColumnInfo(name = "family_name")
    public String familyName;

    @SerializedName("phone_no")
    @ColumnInfo(name = "phone_no")
    public String phoneNo;

    @SerializedName("children_under_18")
    @ColumnInfo(name = "children_under_18")
    public String childrenUnder18;

    @SerializedName("children_below_16")
    @ColumnInfo(name = "children_below_16")
    public String childrenBelow16;

    @SerializedName("children_below_16_in_sch")
    @ColumnInfo(name = "children_below_16_in_sch")
    public String childrenBelow16InSch;

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

    @SerializedName("sources_of_income")
    @ColumnInfo(name = "sources_of_income")
    public String sourcesOfIncome;

    @SerializedName("main_income")
    @ColumnInfo(name = "main_income")
    public String mainIncome ;

    @SerializedName("weekly_earning")
    @ColumnInfo(name = "weekly_earning")
    public String weekEarning;

    @SerializedName("monthly_earning")
    @ColumnInfo(name = "monthly_earning")
    public String monthlyEarning;

    @SerializedName("market_days")
    @ColumnInfo(name = "market_days")
    public String marketDays;

    @SerializedName("cooperative_name")
    @ColumnInfo(name = "cooperative_name")
    public String cooperativeName;

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

    @SerializedName("animal_feed_interest")
    @ColumnInfo(name = "animal_feed_interest")
    public String animalFeedInterest;


    @SerializedName("bags_of_animal_feed")
    @ColumnInfo(name = "bags_of_animal_feed")
    public String bagsOfAnimalFeed;

    @SerializedName("recommendations")
    @ColumnInfo(name = "recommendations")
    public String recommendations ;

    @SerializedName("feedback")
    @ColumnInfo(name = "feedback")
    public String feedback;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getChildrenUnder18() {
        return childrenUnder18;
    }

    public void setChildrenUnder18(String childrenUnder18) {
        this.childrenUnder18 = childrenUnder18;
    }

    public String getChildrenBelow16() {
        return childrenBelow16;
    }

    public void setChildrenBelow16(String childrenBelow16) {
        this.childrenBelow16 = childrenBelow16;
    }

    public String getChildrenBelow16InSch() {
        return childrenBelow16InSch;
    }

    public void setChildrenBelow16InSch(String childrenBelow16InSch) {
        this.childrenBelow16InSch = childrenBelow16InSch;
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

    public String getSourcesOfIncome() {
        return sourcesOfIncome;
    }

    public void setSourcesOfIncome(String sourcesOfIncome) {
        this.sourcesOfIncome = sourcesOfIncome;
    }

    public String getMainIncome() {
        return mainIncome;
    }

    public void setMainIncome(String mainIncome) {
        this.mainIncome = mainIncome;
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

    public String getCooperativeName() {
        return cooperativeName;
    }

    public void setCooperativeName(String cooperativeName) {
        this.cooperativeName = cooperativeName;
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

    public String getAnimalFeedInterest() {
        return animalFeedInterest;
    }

    public void setAnimalFeedInterest(String animalFeedInterest) {
        this.animalFeedInterest = animalFeedInterest;
    }

    public String getBagsOfAnimalFeed() {
        return bagsOfAnimalFeed;
    }

    public void setBagsOfAnimalFeed(String bagsOfAnimalFeed) {
        this.bagsOfAnimalFeed = bagsOfAnimalFeed;
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
        return "BdsDataCollection{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", marital_status='" + marital_status + '\'' +
                ", familyName='" + familyName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", childrenUnder18='" + childrenUnder18 + '\'' +
                ", childrenBelow16='" + childrenBelow16 + '\'' +
                ", childrenBelow16InSch='" + childrenBelow16InSch + '\'' +
                ", adult18Above='" + adult18Above + '\'' +
                ", electoralWard='" + electoralWard + '\'' +
                ", areaCouncil='" + areaCouncil + '\'' +
                ", communityName='" + communityName + '\'' +
                ", sourcesOfIncome='" + sourcesOfIncome + '\'' +
                ", mainIncome='" + mainIncome + '\'' +
                ", weekEarning='" + weekEarning + '\'' +
                ", monthlyEarning='" + monthlyEarning + '\'' +
                ", marketDays='" + marketDays + '\'' +
                ", cooperativeName='" + cooperativeName + '\'' +
                ", litresOfMilkPerDay='" + litresOfMilkPerDay + '\'' +
                ", houseHoldConsumption='" + houseHoldConsumption + '\'' +
                ", milkForSale='" + milkForSale + '\'' +
                ", milkChallenges='" + milkChallenges + '\'' +
                ", cowInAbuja='" + cowInAbuja + '\'' +
                ", totalCow='" + totalCow + '\'' +
                ", milkingCow='" + milkingCow + '\'' +
                ", animalFeedInterest='" + animalFeedInterest + '\'' +
                ", bagsOfAnimalFeed='" + bagsOfAnimalFeed + '\'' +
                ", recommendations='" + recommendations + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
