package com.enyata.camdiary.data.model.api.request;

import com.enyata.camdiary.ui.collections.data.pdsData.PdsDataActivity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PdsDataRequest {


    private  PdsDataRequest(){
        //do not instantiate
    }

    public static class Request {

        @Expose
        @SerializedName("verification_id")
        private String verificationId;

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
        private String marketVisitFrequency;

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
        @SerializedName("referral")
        private String referral;

        @Expose
        @SerializedName("feedback")
        private String feedback;


        public Request(String verificationId, String incomeSources, String mainIncomeSource, String weeklyIncome, String monthlyIncome, String marketVisitFrequency, String dailyVolumeOfMilkByCow, String dailyVolumeOfHouseholdMilkConsumption, String milkVolumeSold, String challenges, String totalAbujaHerd, String totalHerd, String abujaMilkingCows, String referral, String feedback) {
            this.verificationId = verificationId;
            this.incomeSources = incomeSources;
            this.mainIncomeSource = mainIncomeSource;
            this.weeklyIncome = weeklyIncome;
            this.monthlyIncome = monthlyIncome;
            this.marketVisitFrequency = marketVisitFrequency;
            this.dailyVolumeOfMilkByCow = dailyVolumeOfMilkByCow;
            this.dailyVolumeOfHouseholdMilkConsumption = dailyVolumeOfHouseholdMilkConsumption;
            this.milkVolumeSold = milkVolumeSold;
            this.challenges = challenges;
            this.totalAbujaHerd = totalAbujaHerd;
            this.totalHerd = totalHerd;
            this.abujaMilkingCows = abujaMilkingCows;
            this.referral = referral;
            this.feedback = feedback;
        }


        public String getVerificationId() {
            return verificationId;
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

        public String getMarketVisitFrequency() {
            return marketVisitFrequency;
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

        public String getTotalHerd() {
            return totalHerd;
        }

        public String getAbujaMilkingCows() {
            return abujaMilkingCows;
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


            Request request = (Request)obj;

            if (verificationId != null ? !verificationId.equals(request.verificationId) : request.verificationId != null) {
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

            if (marketVisitFrequency != null ? !marketVisitFrequency.equals(request.marketVisitFrequency) : request.marketVisitFrequency != null) {
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
            if (referral != null ? !referral.equals(request.referral) : request.referral != null) {
                return false;
            }

            return feedback != null ? !feedback.equals(request.feedback) : request.feedback != null;

        }


        @Override
        public int hashCode() {
            int result = 0;
            result = 31 * result + (verificationId != null ? verificationId.hashCode() : 0);
            result = 31 * result + (incomeSources != null ? incomeSources.hashCode() : 0);
            result = 31 * result + (mainIncomeSource != null ? mainIncomeSource.hashCode() : 0);
            result = 31 * result + (weeklyIncome != null ? weeklyIncome.hashCode() : 0);
            result = 31 * result + (monthlyIncome != null ? monthlyIncome.hashCode() : 0);
            result = 31 * result + (marketVisitFrequency != null ? marketVisitFrequency.hashCode() : 0);
            result = 31 * result + (dailyVolumeOfMilkByCow != null ? dailyVolumeOfMilkByCow.hashCode() : 0);
            result = 31 * result + (dailyVolumeOfHouseholdMilkConsumption != null ? dailyVolumeOfHouseholdMilkConsumption.hashCode() : 0);
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
