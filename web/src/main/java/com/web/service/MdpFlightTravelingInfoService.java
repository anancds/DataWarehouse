package com.hikvision.mdp.web.service;

import com.hikvision.mdp.web.mapper.MdpFlightTravelingInfoMapper;
import com.hikvision.mdp.web.pojo.MdpFlightTravelingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by geting on 2017/2/10.
 */
@Service
@SuppressWarnings("all")
public class MdpFlightTravelingInfoService {
    @Autowired
    private MdpFlightTravelingInfoMapper mdpFlightTravelingInfoMapper;

    public List<MdpFlightTravelingInfo> queryMdpFlightTravelingInfo(MdpFlightTravelingInfo mdpFlightTravelingInfo){
        List<MdpFlightTravelingInfo> mdpFlightTravelingInfos=null;
        mdpFlightTravelingInfos=this.mdpFlightTravelingInfoMapper.select(mdpFlightTravelingInfo);
        return mdpFlightTravelingInfos;
    }
}
