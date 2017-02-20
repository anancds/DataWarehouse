package com.hikvision.mdp.web.bean;

import com.hikvision.mdp.web.pojo.MdpCommunicationsInfo;

import java.util.List;

/**
 * Created by geting on 2017/1/20.
 */
public class MdpCommunicationsInfoBean {

    private List<MdpCommunicationsInfo> mdpCommunicationsInfos;

    public MdpCommunicationsInfoBean(){

    }

    public MdpCommunicationsInfoBean(List<MdpCommunicationsInfo> mdpCommunicationsInfos){
        this.mdpCommunicationsInfos=mdpCommunicationsInfos;
    }

    public List<MdpCommunicationsInfo> getMdpCommunicationsInfos() {
        return mdpCommunicationsInfos;
    }

    public void setMdpCommunicationsInfos(List<MdpCommunicationsInfo> mdpCommunicationsInfos) {
        this.mdpCommunicationsInfos = mdpCommunicationsInfos;
    }

    @Override
    public String toString() {
        return "MdpCommunicationsInfoBean{" +
                "mdpCommunicationsInfos=" + mdpCommunicationsInfos +
                '}';
    }
}
