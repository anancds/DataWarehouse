package com.hikvision.mdp.web.service;

import com.hikvision.mdp.web.mapper.MdpAccommodationInfoMapper;
import com.hikvision.mdp.web.pojo.MdpAccommodationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by geting on 2017/2/10.
 */

@SuppressWarnings("all")
@Service
public class MdpAccommodationInfoService {

    @Autowired
    private MdpAccommodationInfoMapper mdpAccommodationInfoMapper;

    public List<MdpAccommodationInfo> queryMdpAccommodationInfo(MdpAccommodationInfo mdpAccommodationInfo){
        List<MdpAccommodationInfo> mdpAccommodationInfos=null;
        mdpAccommodationInfos=this.mdpAccommodationInfoMapper.select(mdpAccommodationInfo);
        return mdpAccommodationInfos;
    }

}
