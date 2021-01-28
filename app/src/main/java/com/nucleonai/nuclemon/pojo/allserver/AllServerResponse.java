package com.nucleonai.nuclemon.pojo.allserver;

import java.util.ArrayList;

public class AllServerResponse {
    private ArrayList<AllServerBO> ServerDetails;
    private String message;
    private boolean status;

    public ArrayList<AllServerBO> getServerDetails() {
        if(this.ServerDetails==null){
            this.ServerDetails=new ArrayList<AllServerBO>();
        }
        return ServerDetails;
    }

    public void setServerDetails(ArrayList<AllServerBO> serverDetails) {
        ServerDetails = serverDetails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
