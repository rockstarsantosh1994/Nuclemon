package com.nucleonai.nuclemon.pojo.serverchart;

import java.util.ArrayList;

public class ServerDetailsBO {
    private ArrayList<MemoryDetailsBO> memmoryDetails;

    public ArrayList<MemoryDetailsBO> getMemmoryDetails() {
        if(this.memmoryDetails==null){
            this.memmoryDetails=new ArrayList<MemoryDetailsBO>();
        }
        return memmoryDetails;
    }

    public void setMemmoryDetails(ArrayList<MemoryDetailsBO> memmoryDetails) {
        this.memmoryDetails = memmoryDetails;
    }
}
