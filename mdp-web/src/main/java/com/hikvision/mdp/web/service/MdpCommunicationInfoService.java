package com.hikvision.mdp.web.service;

import com.hikvision.mdp.web.bean.MdpCommunicationsInfoBean;
import com.hikvision.mdp.web.pojo.MdpCommunicationsInfoReflect;
import com.hikvision.mdp.web.mapper.MdpCommunicationMapper;
import com.hikvision.mdp.web.mapper.MdpCommunicationsInfoReflectMapper;
import com.hikvision.mdp.web.pojo.MdpCommunicationsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by geting on 2017/1/19.
 */


@SuppressWarnings("all")
@Service
public class MdpCommunicationInfoService {
    @Autowired
    private MdpCommunicationMapper mdpCommunicationMapper;

    @Autowired
    private MdpCommunicationsInfoReflectMapper mdpCommunicationsInfoReflectMapper;

    /**
     *
     * @return
     */
    public MdpCommunicationsInfoBean queryMdpCommuicationInfo(){return null;}


    /**
     *
     * @param mdpCommunicationsInfo
     * @return
     */



    public Map<String,List<MdpCommunicationsInfoReflect>> queryMdpCommuicationInfoReflect(MdpCommunicationsInfoReflect mdpCommunicationsInfoReflect){





        List<MdpCommunicationsInfoReflect> mdpCommunicationsInfoReflects= this.mdpCommunicationsInfoReflectMapper.select(mdpCommunicationsInfoReflect);

        Map<String,List<MdpCommunicationsInfoReflect>> infoMap=null;



        if(mdpCommunicationsInfoReflects!=null&&mdpCommunicationsInfoReflects.size()>0){
            infoMap=new HashMap<String,List<MdpCommunicationsInfoReflect>>();

            for (MdpCommunicationsInfoReflect mdpCInfoReflect : mdpCommunicationsInfoReflects) {
                if (!infoMap.containsKey(mdpCInfoReflect.getBusinessType())) {
                    infoMap.put(mdpCInfoReflect.getBusinessType(), new ArrayList<MdpCommunicationsInfoReflect>());
                }
                infoMap.get(mdpCInfoReflect.getBusinessType()).add(mdpCInfoReflect);
            }

            // mdpCommunicationsInfoBean=new MdpCommunicationsInfoBean(mdpCommunicationsInfos);
        }
        return infoMap;

    }



    public Map<String,List<MdpCommunicationsInfo>> queryMdpCommuicationInfo(MdpCommunicationsInfo mdpCommunicationsInfo){

        MdpCommunicationsInfoBean mdpCommunicationsInfoBean=null;



        List<MdpCommunicationsInfo> mdpCommunicationsInfos= this.mdpCommunicationMapper.select(mdpCommunicationsInfo);

        Map<String,List<MdpCommunicationsInfo>> infoMap=null;



        if(mdpCommunicationsInfos!=null&&mdpCommunicationsInfos.size()>0){
           infoMap=new HashMap<String,List<MdpCommunicationsInfo>>();

            for (MdpCommunicationsInfo mdpCInfo : mdpCommunicationsInfos) {
                if (!infoMap.containsKey(mdpCInfo.getBusinessType())) {
                    infoMap.put(mdpCInfo.getBusinessType(), new ArrayList<MdpCommunicationsInfo>());
                }
                infoMap.get(mdpCInfo.getBusinessType()).add(mdpCInfo);
            }

           // mdpCommunicationsInfoBean=new MdpCommunicationsInfoBean(mdpCommunicationsInfos);
        }
        return infoMap;

    }
}
