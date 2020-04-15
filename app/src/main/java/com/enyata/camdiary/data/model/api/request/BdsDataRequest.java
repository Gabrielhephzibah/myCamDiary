package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BdsDataRequest {

    private  BdsDataRequest(){
        // do not intialize
    }

    public  static  class Request{
        @Expose
        @SerializedName("image_url")
        private String imageUrl;

        @Expose
        @SerializedName("first_name")
        private String firstName;

        @Expose
        @SerializedName("last_name")
        private String lastName;

        @Expose
        @SerializedName("gender")
        private String gender;

        @Expose
        @SerializedName("age")
        private String age;

        @Expose
        @SerializedName("marital_status")
        private String maritalStatus;

        @Expose
        @SerializedName("household_name")
        private String householdName;

        @Expose
        @SerializedName("contact_no")
        private String contactNo;

        @Expose
        @SerializedName("ward")
        private String ward;

        @Expose
        @SerializedName("area_council")
        private String areaCouncil;

        @Expose
        @SerializedName("community_name")
        private String communityName;

        @Expose
        @SerializedName("cooperative_name")
        private String cooperativeName;


        @Expose
        @SerializedName("income_sources")
        private String incomeSources;

        @Expose
        @SerializedName("main_income_source")
        private String mainIncomeSource;

        @Expose
        @SerializedName("weekly_income")
        private String weeklyIncome;

        @Expose
        @SerializedName("monthly_income")
        private String monthlyIncome;

        @Expose
        @SerializedName("market_visit_frequency")
        private String marketVisitfrequency;

        @Expose
        @SerializedName("children_below_18")
        private String childrenBelow18;

        @Expose
        @SerializedName("children_below_16")
        private String childrenBelow16;

        @Expose
        @SerializedName("children_below_16_inschl")
        private String childrenBelow16Inschl;

        @Expose
        @SerializedName("householdmember_18_above")
        private String householdmember18Above;

        @Expose
        @SerializedName("daily_volume_of_milk_by_cow")
        private String dailyVolumeOfMilkByCow;

        @Expose
        @SerializedName("daily_volume_of_household_milk_consumption")
        private String dailyVolumeOfHouseholdMilkConsumption;

        @Expose
        @SerializedName("milk_volume_sold")
        private String milkVolumeSold;

        @Expose
        @SerializedName("challenges")
        private String challenges;

        @Expose
        @SerializedName("total_abuja_herd")
        private String totalAbujaHerd;

        @Expose
        @SerializedName("total_herd")
        private String totalHerd;

        @Expose
        @SerializedName("abuja_milking_cows")
        private String abujaMilkingCows;

        @Expose
        @SerializedName("animal_feed_interest")
        private String animalFeedInterest;

        @Expose
        @SerializedName("animal_feed_requirement")
        private String animalFeedRequirement;

        @Expose
        @SerializedName("referral")
        private String referral;

        @Expose
        @SerializedName("feedback")
        private String feedback;

        public Request(String imageUrl, String firstName, String lastName, String gender, String age, String maritalStatus, String householdName, String contactNo, String ward, String areaCouncil, String communityName, String cooperativeName, String incomeSources, String mainIncomeSource, String weeklyIncome, String monthlyIncome, String marketVisitfrequency, String childrenBelow18, String childrenBelow16, String childrenBelow16Inschl, String householdmember18Above, String dailyVolumeOfMilkByCow, String dailyVolumeOfHouseholdMilkConsumption, String milkVolumeSold, String challenges, String totalAbujaHerd, String totalHerd, String abujaMilkingCows, String animalFeedInterest, String animalFeedRequirement, String referral, String feedback) {
            this.imageUrl = imageUrl;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.maritalStatus = maritalStatus;
            this.householdName = householdName;
            this.contactNo = contactNo;
            this.ward = ward;
            this.areaCouncil = areaCouncil;
            this.communityName = communityName;
            this.cooperativeName = cooperativeName;
            this.incomeSources = incomeSources;
            this.mainIncomeSource = mainIncomeSource;
            this.weeklyIncome = weeklyIncome;
            this.monthlyIncome = monthlyIncome;
            this.marketVisitfrequency = marketVisitfrequency;
            this.childrenBelow18 = childrenBelow18;
            this.childrenBelow16 = childrenBelow16;
            this.childrenBelow16Inschl = childrenBelow16Inschl;
            this.householdmember18Above = householdmember18Above;
            this.dailyVolumeOfMilkByCow = dailyVolumeOfMilkByCow;
            this.dailyVolumeOfHouseholdMilkConsumption = dailyVolumeOfHouseholdMilkConsumption;
            this.milkVolumeSold = milkVolumeSold;
            this.challenges = challenges;
            this.totalAbujaHerd = totalAbujaHerd;
            this.totalHerd = totalHerd;
            this.abujaMilkingCows = abujaMilkingCows;
            this.animalFeedInterest = animalFeedInterest;
            this.animalFeedRequirement = animalFeedRequirement;
            this.referral = referral;
            this.feedback = feedback;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getGender() {
            return gender;
        }

        public String getAge() {
            return age;
        }

        public String getMaritalStatus() {
            return maritalStatus;
        }

        public String getHouseholdName() {
            return householdName;
        }

        public String getContactNo() {
            return contactNo;
        }

        public String getWard() {
            return ward;
        }

        public String getAreaCouncil() {
            return areaCouncil;
        }

        public String getCommunityName() {
            return communityName;
        }

        public String getCooperativeName() {
            return cooperativeName;
        }

        public String getIncomeSources() {
            return incomeSources;
        }

        public String getMainIncomeSource() {
            return mainIncomeSource;
        }

        public String getWeeklyIncome() {
            return weeklyIncome;
        }

        public String getMonthlyIncome() {
            return monthlyIncome;
        }

        public String getMarketVisitfrequency() {
            return marketVisitfrequency;
        }

        public String getChildrenBelow18() {
            return childrenBelow18;
        }

        public String getChildrenBelow16() {
            return childrenBelow16;
        }

        public String getChildrenBelow16Inschl() {
            return childrenBelow16Inschl;
        }

        public String getHouseholdmember18Above() {
            return householdmember18Above;
        }

        public String getDailyVolumeOfMilkByCow() {
            return dailyVolumeOfMilkByCow;
        }

        public String getDailyVolumeOfHouseholdMilkConsumption() {
            return dailyVolumeOfHouseholdMilkConsumption;
        }

        public String getMilkVolumeSold() {
            return milkVolumeSold;
        }

        public String getChallenges() {
            return challenges;
        }

        public String getTotalAbujaHerd() {
            return totalAbujaHerd;
        }

        public String getTotal_herd() {
            return totalHerd;
        }

        public String getAbujaMilkingCows() {
            return abujaMilkingCows;
        }

        public String getAnimalFeedInterest() {
            return animalFeedInterest;
        }

        public String getAnimalFeedRequirement() {
            return animalFeedRequirement;
        }

        public String getReferral() {
            return referral;
        }

        public String getFeedback() {
            return feedback;
        }



        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }


           BdsDataRequest.Request request = (Request)obj;

            if (imageUrl != null ? !imageUrl.equals(request.imageUrl) : request.imageUrl != null) {
                return false;
            }
            if (firstName != null ? !firstName.equals(request.firstName) : request.firstName != null) {
                return false;
            }
            if (lastName != null ? !lastName.equals(request.lastName) : request.lastName != null) {
                return false;
            }
            if (gender != null ? !gender.equals(request.gender) : request.gender != null) {
                return false;
            }
            if (age != null ? !age.equals(request.age) : request.age != null) {
                return false;
            }
            if (maritalStatus != null ? !maritalStatus.equals(request.maritalStatus) : request.maritalStatus != null) {
                return false;
            }
            if (householdName != null ? !householdName.equals(request.householdName) : request.householdName != null) {
                return false;
            }
            if (contactNo != null ? !contactNo.equals(request.contactNo) : request.contactNo != null) {
                return false;
            }
            if (areaCouncil != null ? !areaCouncil.equals(request.areaCouncil) : request.areaCouncil != null) {
                return false;
            }
            if (ward != null ? !ward.equals(request.ward) : request.ward != null) {
                return false;
            }
            if (communityName != null ? !communityName.equals(request.communityName) : request.communityName != null) {
                return false;
            }
            if (cooperativeName != null ? !cooperativeName.equals(request.cooperativeName) : request.cooperativeName != null) {
                return false;
            }
            if (incomeSources != null ? !incomeSources.equals(request.incomeSources) : request.incomeSources != null) {
                return false;
            }
            if (mainIncomeSource != null ? !mainIncomeSource.equals(request.mainIncomeSource) : request.mainIncomeSource != null) {
                return false;
            }
            if (weeklyIncome != null ? !weeklyIncome.equals(request.weeklyIncome) : request.weeklyIncome != null) {
                return false;
            }
            if (monthlyIncome != null ? !monthlyIncome.equals(request.monthlyIncome) : request.monthlyIncome != null) {
                return false;
            }
            if (marketVisitfrequency != null ? !marketVisitfrequency.equals(request.marketVisitfrequency) : request.marketVisitfrequency != null) {
                return false;
            }
            if (childrenBelow18 != null ? !childrenBelow18.equals(request.childrenBelow18) : request.childrenBelow18 != null) {
                return false;
            }
            if (childrenBelow16 != null ? !childrenBelow16.equals(request.childrenBelow16) : request.childrenBelow16 != null) {
                return false;
            }
            if (childrenBelow16Inschl != null ? !childrenBelow16Inschl.equals(request.childrenBelow16Inschl) : request.childrenBelow16Inschl != null) {
                return false;
            }
            if (householdmember18Above != null ? !householdmember18Above.equals(request.householdmember18Above) : request.householdmember18Above != null) {
                return false;
            }
            if (dailyVolumeOfMilkByCow != null ? !dailyVolumeOfMilkByCow.equals(request.dailyVolumeOfMilkByCow) : request.dailyVolumeOfMilkByCow != null) {
                return false;
            }
            if (dailyVolumeOfHouseholdMilkConsumption != null ? !dailyVolumeOfHouseholdMilkConsumption.equals(request.dailyVolumeOfHouseholdMilkConsumption) : request.dailyVolumeOfHouseholdMilkConsumption != null) {
                return false;
            }
            if (milkVolumeSold != null ? !milkVolumeSold.equals(request.milkVolumeSold) : request.milkVolumeSold != null) {
                return false;
            }
            if (challenges != null ? !challenges.equals(request.challenges) : request.challenges != null) {
                return false;
            }

            if (totalAbujaHerd != null ? !totalAbujaHerd.equals(request.totalAbujaHerd) : request.totalAbujaHerd != null) {
                return false;
            }
            if (totalHerd != null ? !totalHerd.equals(request.totalHerd) : request.totalHerd != null) {
                return false;
            }
            if (abujaMilkingCows != null ? !abujaMilkingCows.equals(request.abujaMilkingCows) : request.abujaMilkingCows != null) {
                return false;
            }
            if (animalFeedInterest != null ? !animalFeedInterest.equals(request.animalFeedInterest) : request.animalFeedInterest != null) {
                return false;
            }
            if (animalFeedRequirement != null ? !animalFeedRequirement.equals(request.animalFeedRequirement) : request.animalFeedRequirement != null) {
                return false;
            }
            if (referral != null ? !referral.equals(request.referral) : request.referral != null) {
                return false;
            }

            return feedback != null ? !feedback.equals(request.feedback) : request.feedback != null;

        }



        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
            result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            result = 31 * result + (gender != null ? gender.hashCode() : 0);
            result = 31 * result + (age != null ? age.hashCode() : 0);
            result = 31 * result + (maritalStatus != null ? maritalStatus.hashCode() : 0);
            result = 31 * result + (contactNo != null ? contactNo.hashCode() : 0);
            result = 31 * result + (ward != null ? ward.hashCode() : 0);
            result = 31 * result + (householdName != null ? householdName.hashCode() : 0);
            result = 31 * result + (areaCouncil != null ? areaCouncil.hashCode() : 0);
            result = 31 * result + (communityName != null ? communityName.hashCode() : 0);
            result = 31 * result + (cooperativeName != null ? cooperativeName.hashCode() : 0);
            result = 31 * result + (incomeSources != null ? incomeSources.hashCode() : 0);
            result = 31 * result + (mainIncomeSource != null ? mainIncomeSource.hashCode() : 0);
            result = 31 * result + (weeklyIncome != null ? weeklyIncome.hashCode() : 0);
            result = 31 * result + (monthlyIncome != null ? monthlyIncome.hashCode() : 0);
            result = 31 * result + (marketVisitfrequency != null ? marketVisitfrequency.hashCode() : 0);
            result = 31 * result + (childrenBelow18 != null ? childrenBelow18.hashCode() : 0);
            result = 31 * result + (childrenBelow16 != null ? childrenBelow16.hashCode() : 0);
            result = 31 * result + (childrenBelow16Inschl != null ? childrenBelow16Inschl.hashCode() : 0);
            result = 31 * result + (householdmember18Above != null ? householdmember18Above.hashCode() : 0);
            result = 31 * result + (dailyVolumeOfHouseholdMilkConsumption != null ? dailyVolumeOfHouseholdMilkConsumption.hashCode() : 0);
            result = 31 * result + (animalFeedInterest != null ? animalFeedInterest.hashCode() : 0);
            result = 31 * result + (animalFeedRequirement != null ? animalFeedRequirement.hashCode() : 0);
            result = 31 * result + (dailyVolumeOfMilkByCow != null ? dailyVolumeOfMilkByCow.hashCode() : 0);
            result = 31 * result + (milkVolumeSold != null ? milkVolumeSold.hashCode() : 0);
            result = 31 * result + (challenges != null ? challenges.hashCode() : 0);
            result = 31 * result + (totalAbujaHerd != null ? totalAbujaHerd.hashCode() : 0);
            result = 31 * result + (totalHerd != null ? totalHerd.hashCode() : 0);
            result = 31 * result + (abujaMilkingCows != null ? abujaMilkingCows.hashCode() : 0);
            result = 31 * result + (referral != null ? referral.hashCode() : 0);
            result = 31 * result + (feedback != null ? feedback.hashCode() : 0);

            return  result;


        }

    }

}
