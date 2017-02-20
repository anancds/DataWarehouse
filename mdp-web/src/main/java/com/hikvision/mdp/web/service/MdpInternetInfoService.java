package com.hikvision.mdp.web.service;

import com.hikvision.mdp.web.mapper.MdpFlightTravelingInfoMapper;
import com.hikvision.mdp.web.mapper.MdpInternetInfoMapper;
import com.hikvision.mdp.web.pojo.MdpFlightTravelingInfo;
import com.hikvision.mdp.web.pojo.MdpInternetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by geting on 2017/2/10.
 */

@Service
@SuppressWarnings("all")
public class MdpInternetInfoService {
    @Autowired
    private MdpInternetInfoMapper mdpInternetInfoMapper;

    public List<MdpInternetInfo> queryMdpInternetInfo(MdpInternetInfo mdpInternetInfo){
        List<MdpInternetInfo> mdpInternetInfos=null;
        mdpInternetInfos=this.mdpInternetInfoMapper.select(mdpInternetInfo);
        return mdpInternetInfos;
    }
}
