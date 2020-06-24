package com.enyata.camdiary.data.model.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class MilkCollection implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "farmer_id")
    private  String farmerId;

    @ColumnInfo(name = "volume")
    private String volume;

    @ColumnInfo(name = "churn_no")
    private String churnNo;

    @ColumnInfo(name = "status")
    private String status;


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

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getChurnNo() {
        return churnNo;
    }

    public void setChurnNo(String churnNo) {
        this.churnNo = churnNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MilkCollection{" +
                "id=" + id +
                ", farmerId='" + farmerId + '\'' +
                ", volume='" + volume + '\'' +
                ", churnNo='" + churnNo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
