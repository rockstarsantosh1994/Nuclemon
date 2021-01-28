package com.nucleonai.nuclemon.pojo;

public class DemoBO1 {
    private String name;
    private String port;
    private String instanceName;
    private String authentication;
    private String userName;
    private String lastConnectionTime;

    public DemoBO1(String name, String port, String instanceName, String authentication, String userName, String lastConnectionTime) {
        this.name = name;
        this.port = port;
        this.instanceName = instanceName;
        this.authentication = authentication;
        this.userName = userName;
        this.lastConnectionTime = lastConnectionTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastConnectionTime() {
        return lastConnectionTime;
    }

    public void setLastConnectionTime(String lastConnectionTime) {
        this.lastConnectionTime = lastConnectionTime;
    }
}
