package com.nucleonai.nuclemon.pojo.serverchart;

public class ServerChartResponse {

    private ServerDetailsBO ServerDetails;
    private String message;
    private boolean status;

    public ServerDetailsBO getServerDetails() {
        return ServerDetails;
    }

    public void setServerDetails(ServerDetailsBO serverDetails) {
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
