package com.nucleonai.nuclemon.pojo.dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DashBoardResponse {

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("totalServer")
    @Expose
    private String totalServer;

    @SerializedName("upServer")
    @Expose
    private String upServer;

    @SerializedName("downServer")
    @Expose
    private String downServer;

    @SerializedName("services")
    @Expose
    private String services;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("DashboardDetails")
    @Expose
    private ArrayList<DashBoardDetailsBO> dashBoardDetailsBOArrayList;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTotalServer() {
        if(this.totalServer==null){
            this.totalServer="0";
        }
        return totalServer;
    }

    public void setTotalServer(String totalServer) {
        this.totalServer = totalServer;
    }

    public String getUpServer() {
        if(this.upServer==null){
            this.upServer="0";
        }
        return upServer;
    }

    public void setUpServer(String upServer) {
        this.upServer = upServer;
    }

    public String getDownServer() {
        if(this.downServer==null)
        {
            this.downServer="0";
        }
        return downServer;
    }

    public void setDownServer(String downServer) {
        this.downServer = downServer;
    }

    public String getServices() {
        if(this.services==null){
            this.services="0";
        }
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<DashBoardDetailsBO> getDashBoardDetailsBOArrayList() {
        if(this.dashBoardDetailsBOArrayList==null){
            this.dashBoardDetailsBOArrayList=new ArrayList<DashBoardDetailsBO>();
        }
        return dashBoardDetailsBOArrayList;
    }

    public void setDashBoardDetailsBOArrayList(ArrayList<DashBoardDetailsBO> dashBoardDetailsBOArrayList) {
        this.dashBoardDetailsBOArrayList = dashBoardDetailsBOArrayList;
    }
}

