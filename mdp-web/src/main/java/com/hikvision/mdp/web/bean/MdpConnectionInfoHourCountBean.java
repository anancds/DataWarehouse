package com.hikvision.mdp.web.bean;

import com.hikvision.mdp.web.pojo.MdpConnectionInfoHourCount;

import java.util.List;

/**
 * Created by geting on 2017/1/24.
 */
public class MdpConnectionInfoHourCountBean {

    private List<MdpConnectionInfoHourCount> mdpConnectionInfoHourCounts;

    public MdpConnectionInfoHourCountBean(){

    }

    public MdpConnectionInfoHourCountBean(List<MdpConnectionInfoHourCount> mdpConnectionInfoHourCounts){
        this.mdpConnectionInfoHourCounts=mdpConnectionInfoHourCounts;

    }

    @Override
    public String toString() {
        return "MdpConnectionInfoHourCountBean{" +
                "mdpConnectionInfoHourCounts=" + mdpConnectionInfoHourCounts +
                '}';
    }

    public List<MdpConnectionInfoHourCount> getMdpConnectionInfoHourCounts() {
        return mdpConnectionInfoHourCounts;
    }

    public void setMdpConnectionInfoHourCounts(List<MdpConnectionInfoHourCount> mdpConnectionInfoHourCounts) {
        this.mdpConnectionInfoHourCounts = mdpConnectionInfoHourCounts;
    }
}
