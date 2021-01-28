package com.nucleonai.nuclemon.pojo;

public class DemoBO {

    private String name;

    private String serverCount;
    private String serverName;
    private int serverColor;
    private String serverSubName;

    public DemoBO(String serverCount, String serverName, int serverColor) {
        this.serverCount = serverCount;
        this.serverName = serverName;
        this.serverColor = serverColor;
    }

    public DemoBO(String name, String serverName, int serverColor, String serverSubName) {
        this.name = name;

        this.serverName = serverName;
        this.serverColor = serverColor;
        this.serverSubName = serverSubName;
    }

    public String getServerCount() {
        return serverCount;
    }

    public void setServerCount(String serverCount) {
        this.serverCount = serverCount;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getServerColor() {
        return serverColor;
    }

    public void setServerColor(int serverColor) {
        this.serverColor = serverColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServerSubName() {
        return serverSubName;
    }

    public void setServerSubName(String serverSubName) {
        this.serverSubName = serverSubName;
    }
}
