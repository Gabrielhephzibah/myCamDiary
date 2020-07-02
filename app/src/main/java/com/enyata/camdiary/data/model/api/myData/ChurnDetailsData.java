package com.enyata.camdiary.data.model.api.myData;

import com.enyata.camdiary.utils.InternetConnection;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ChurnDetailsData {

    @Expose
    @SerializedName("churn_id")
    private String churnId ;


    @Expose
    @SerializedName("volume")
    private String volume ;


    public ChurnDetailsData(String churnId, String volume) {
        this.churnId = churnId;
        this.volume = volume;
    }

    public String getChurnId() {
        return churnId;
    }

    public void setChurnId(String churnId) {
        this.churnId = churnId;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChurnDetailsData request = (ChurnDetailsData) o;
        return Objects.equals(churnId, request.churnId) &&
                Objects.equals(volume, request.volume);

    }


    @Override
    public int hashCode() {
        int result = Objects.hash(churnId);
        result = 31 * result + Objects.hashCode(volume);

        return result;
    }


    @Override
    public String toString() {
        return "ChurnDetailsData{" +
                "churnId='" + churnId + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}
