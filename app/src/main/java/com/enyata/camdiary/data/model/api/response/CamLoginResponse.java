package com.enyata.camdiary.data.model.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CamLoginResponse {

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private String data;


    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        CamLoginResponse that = (CamLoginResponse) object;

        if (status != null ? !that.status.equals(status) : that.status != null) {
            return false;
        }
        if (data != null ? !that.data.equals(data) : that.data != null) {
            return false;
        }
        return message != null ? that.message.equals(message) : that.message == null;

    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

}
