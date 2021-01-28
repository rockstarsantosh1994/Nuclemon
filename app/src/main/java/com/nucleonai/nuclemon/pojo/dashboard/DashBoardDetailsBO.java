package com.nucleonai.nuclemon.pojo.dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DashBoardDetailsBO {

    @SerializedName("serverName")
    @Expose
    private String serverName;

    @SerializedName("cpuUtilization")
    @Expose
    private int cpuUtilization=0;

    @SerializedName("memmoryUtilization")
    @Expose
    private int memmoryUtilization=0;

    @SerializedName("driveUtilization")
    @Expose
    private ArrayList<DriveUtilizationBO> driveUtilization;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(int cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public int getMemmoryUtilization() {
        return memmoryUtilization;
    }

    public void setMemmoryUtilization(int memmoryUtilization) {
        this.memmoryUtilization = memmoryUtilization;
    }

    public ArrayList<DriveUtilizationBO> getDriveUtilization() {
        if(this.driveUtilization==null){
            this.driveUtilization=new ArrayList<>();
        }
        return driveUtilization;
    }

    public void setDriveUtilization(ArrayList<DriveUtilizationBO> driveUtilization) {
        this.driveUtilization = driveUtilization;
    }
}
