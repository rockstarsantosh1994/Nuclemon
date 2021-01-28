package com.nucleonai.nuclemon.pojo.dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriveUtilizationBO {

    @SerializedName("instance")
    @Expose
    private String instance;

    @SerializedName("volumeMountPoint")
    @Expose
    private String volumeMountPoint;

    @SerializedName("utilizedsizeGB")
    @Expose
    private int utilizedsizeGB=0;

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getVolumeMountPoint() {
        return volumeMountPoint;
    }

    public void setVolumeMountPoint(String volumeMountPoint) {
        this.volumeMountPoint = volumeMountPoint;
    }

    public int getUtilizedsizeGB() {
        return utilizedsizeGB;
    }

    public void setUtilizedsizeGB(int utilizedsizeGB) {
        this.utilizedsizeGB = utilizedsizeGB;
    }
}
