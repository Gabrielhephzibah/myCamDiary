package com.enyata.camdiary.data.model.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class CdsDataRequest {

    private  CdsDataRequest(){
        //do not instantiate
    }

    public static class Request {

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
        @SerializedName("householdmember_18_above")
        private String householdmember18Above;

        @Expose
        @SerializedName("daily_volume_of_milk_by_cow")
        private String dailyVolumeOfMilkByCow;

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
        @SerializedName("feedback")
        private String feedback;


        public Request(String firstName, String lastName, String gender, String age, String maritalStatus, String contactNo, String ward, String areaCouncil, String communityName, String incomeSources, String mainIncomeSource, String weeklyIncome, String monthlyIncome, String householdmember18Above, String dailyVolumeOfMilkByCow, String milkVolumeSold, String challenges, String totalAbujaHerd, String totalHerd, String abujaMilkingCows, String feedback) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.maritalStatus = maritalStatus;
            this.contactNo = contactNo;
            this.ward = ward;
            this.areaCouncil = areaCouncil;
            this.communityName = communityName;
            this.incomeSources = incomeSources;
            this.mainIncomeSource = mainIncomeSource;
            this.weeklyIncome = weeklyIncome;
            this.monthlyIncome = monthlyIncome;
            this.householdmember18Above = householdmember18Above;
            this.dailyVolumeOfMilkByCow = dailyVolumeOfMilkByCow;
            this.milkVolumeSold = milkVolumeSold;
            this.challenges = challenges;
            this.totalAbujaHerd = totalAbujaHerd;
            this.totalHerd = totalHerd;
            this.abujaMilkingCows = abujaMilkingCows;
            this.feedback = feedback;
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

        public String getHouseholdmember18Above() {
            return householdmember18Above;
        }

        public String getDailyVolumeOfMilkByCow() {
            return dailyVolumeOfMilkByCow;
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

        public String getTotalHerd() {
            return totalHerd;
        }

        public String getAbujaMilkingCows() {
            return abujaMilkingCows;
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


            Request request = (Request)obj;

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

            if (contactNo != null ? !contactNo.equals(request.contactNo) : request.contactNo != null) {
                return false;
            }

            if (ward != null ? !ward.equals(request.ward) : request.ward != null) {
                return false;
            }

            if (areaCouncil != null ? !areaCouncil.equals(request.areaCouncil) : request.areaCouncil != null) {
                return false;
            }

            if (communityName != null ? !communityName.equals(request.communityName) : request.communityName != null) {
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
            if (householdmember18Above != null ? !householdmember18Above.equals(request.householdmember18Above) : request.householdmember18Above != null) {
                return false;
            }
            if (dailyVolumeOfMilkByCow != null ? !dailyVolumeOfMilkByCow.equals(request.dailyVolumeOfMilkByCow) : request.dailyVolumeOfMilkByCow != null) {
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

            return feedback != null ? !feedback.equals(request.feedback) : request.feedback != null;

        }


        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
            result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
            result = 31 * result + (gender != null ? gender.hashCode() : 0);
            result = 31 * result + (age != null ? age.hashCode() : 0);
            result = 31 * result + (contactNo != null ? contactNo.hashCode() : 0);
            result = 31 * result + (ward != null ? ward.hashCode() : 0);
            result = 31 * result + (areaCouncil != null ? areaCouncil.hashCode() : 0);
            result = 31 * result + (communityName != null ? communityName.hashCode() : 0);
            result = 31 * result + (incomeSources != null ? incomeSources.hashCode() : 0);
            result = 31 * result + (mainIncomeSource != null ? mainIncomeSource.hashCode() : 0);
            result = 31 * result + (weeklyIncome != null ? weeklyIncome.hashCode() : 0);
            result = 31 * result + (monthlyIncome != null ? monthlyIncome.hashCode() : 0);
            result = 31 * result + (householdmember18Above != null ? householdmember18Above.hashCode() : 0);
            result = 31 * result + (dailyVolumeOfMilkByCow != null ? dailyVolumeOfMilkByCow.hashCode() : 0);
            result = 31 * result + (milkVolumeSold != null ? milkVolumeSold.hashCode() : 0);
            result = 31 * result + (challenges != null ? challenges.hashCode() : 0);
            result = 31 * result + (totalAbujaHerd != null ? totalAbujaHerd.hashCode() : 0);
            result = 31 * result + (totalHerd != null ? totalHerd.hashCode() : 0);
            result = 31 * result + (abujaMilkingCows != null ? abujaMilkingCows.hashCode() : 0);
            result = 31 * result + (feedback != null ? feedback.hashCode() : 0);

            return  result;


        }


    }


}
