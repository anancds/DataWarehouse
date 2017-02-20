package com.hikvision.mdp.web.service;

import com.hikvision.mdp.web.bean.MdpCommunicationsInfoBean;
import com.hikvision.mdp.web.bean.MdpConnectionInfoHourCountBean;
import com.hikvision.mdp.web.mapper.MdpConnectionInfoHourCountMapper;
import com.hikvision.mdp.web.pojo.MdpCommunicationsInfo;
import com.hikvision.mdp.web.pojo.MdpConnectionInfoHourCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by geting on 2017/1/24.
 */

@SuppressWarnings("all")
@Service
public class MdpConnectionInfoHourCountService {

    @Autowired
    private MdpConnectionInfoHourCountMapper mdpConnectionInfoHourCountMapper;

    public Map<String,List<MdpConnectionInfoHourCount>> queryMdpConnectionInfoHourCount(MdpConnectionInfoHourCount mdpConnectionInfoHourCount) {

        MdpConnectionInfoHourCountBean mdpConnectionInfoHourCountBean=null;

        List<MdpConnectionInfoHourCount> mdpConnectionInfoHourCountList=this.mdpConnectionInfoHourCountMapper.select(mdpConnectionInfoHourCount);

        Map<String,List<MdpConnectionInfoHourCount>> infoMap=null;

       /* if(mdpConnectionInfoHourCountList!=null&&mdpConnectionInfoHourCountList.size()>0){


            mdpConnectionInfoHourCountBean=new MdpConnectionInfoHourCountBean(mdpConnectionInfoHourCountList);
        }

        return mdpConnectionInfoHourCountBean;*/



        if(mdpConnectionInfoHourCountList!=null&&mdpConnectionInfoHourCountList.size()>0){
            infoMap=new HashMap<String,List<MdpConnectionInfoHourCount>>();
            for (MdpConnectionInfoHourCount mdpCInfoCount : mdpConnectionInfoHourCountList) {
                if (!infoMap.containsKey(mdpCInfoCount.getType())) {
                    infoMap.put(mdpCInfoCount.getType(), new ArrayList<MdpConnectionInfoHourCount>());
                }
                infoMap.get(mdpCInfoCount.getType()).add(mdpCInfoCount);
            }

            // mdpCommunicationsInfoBean=new MdpCommunicationsInfoBean(mdpCommunicationsInfos);
        }
        return infoMap;


    }
}
