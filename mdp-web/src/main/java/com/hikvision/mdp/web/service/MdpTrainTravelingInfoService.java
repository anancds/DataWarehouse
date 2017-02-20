package com.hikvision.mdp.web.service;

import com.hikvision.mdp.web.mapper.MdpInternetInfoMapper;
import com.hikvision.mdp.web.mapper.MdpTrainTravelingInfoMapper;
import com.hikvision.mdp.web.pojo.MdpInternetInfo;
import com.hikvision.mdp.web.pojo.MdpTrainTravelingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by geting on 2017/2/10.
 */
@SuppressWarnings("all")
@Service
public class MdpTrainTravelingInfoService {
    @Autowired
    private MdpTrainTravelingInfoMapper mdpTrainTravelingInfoMapper;

    public List<MdpTrainTravelingInfo> queryMdpTrainTravelingInfo(MdpTrainTravelingInfo mdpTrainTravelingInfo){
        List<MdpTrainTravelingInfo> mdpTrainTravelingInfos=null;
        mdpTrainTravelingInfos=this.mdpTrainTravelingInfoMapper.select(mdpTrainTravelingInfo);
        return mdpTrainTravelingInfos;
    }
}
