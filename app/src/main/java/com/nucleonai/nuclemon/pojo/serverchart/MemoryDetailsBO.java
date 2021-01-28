package com.nucleonai.nuclemon.pojo.serverchart;

public class MemoryDetailsBO {

    private int cpuUtilization;
    private int memmoryUtiliation;
    private String data_sample_timestamp;
    private String instance;

    public int getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(int cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public int getMemmoryUtiliation() {
        return memmoryUtiliation;
    }

    public void setMemmoryUtiliation(int memmoryUtiliation) {
        this.memmoryUtiliation = memmoryUtiliation;
    }

    public String getData_sample_timestamp() {
        return data_sample_timestamp;
    }

    public void setData_sample_timestamp(String data_sample_timestamp) {
        this.data_sample_timestamp = data_sample_timestamp;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    @Override
    public String toString() {
        return "MemoryDetailsBO{" +
                "cpuUtilization=" + cpuUtilization +
                ", memmoryUtiliation=" + memmoryUtiliation +
                ", data_sample_timestamp='" + data_sample_timestamp + '\'' +
                ", instance='" + instance + '\'' +
                '}';
    }
}