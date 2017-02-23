package com.hikvision.mdp.web.controller;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hikvision.mdp.web.bean.MdpCommunicationsInfoBean;
import com.hikvision.mdp.web.bean.MdpConnectionInfoHourCountBean;
import com.hikvision.mdp.web.pojo.*;
import com.hikvision.mdp.web.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by geting on 2017/1/19.
 */


@Controller
@RequestMapping("info")
public class MdpFullDetailInfoController {

    @Autowired
    private MdpCommunicationInfoService mdpCommunicationInfoService;

    @Autowired
    private MdpConnectionInfoHourCountService mdpConnectionInfoHourCountService;

    @Autowired
    private MdpAccommodationInfoService mdpAccommodationInfoService;
    @Autowired
    private MdpInternetInfoService mdpInternetInfoService;
    @Autowired
    private MdpFlightTravelingInfoService mdpFlightTravelingInfoService;
    @Autowired
    private MdpTrainTravelingInfoService mdpTrainTravelingInfoService;



    private static final ObjectMapper MAPPER = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);


       public ResponseEntity<String> getCommunicationsInfoDirect(String callback, MdpCommunicationsInfo mdpCommunicationsInfo) {
        try {
            MdpCommunicationsInfoBean mdpCommunicationsInfoBean = this.mdpCommunicationInfoService.queryMdpCommuicationInfo();
            if (mdpCommunicationsInfoBean != null) {
                if (StringUtils.isEmpty(callback))
                    return ResponseEntity.ok(MAPPER.writeValueAsString(mdpCommunicationsInfoBean));
                return ResponseEntity.ok(callback + "(" + MAPPER.writeValueAsString(mdpCommunicationsInfoBean) + ");");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(value = "phone-commuication", method = RequestMethod.GET)
    public ResponseEntity<Map<String,List<MdpCommunicationsInfoReflect>>> getCommunicationsInfoReflect(MdpCommunicationsInfoReflect mdpCommunicationsInfoReflect){
        try {
            Map<String,List<MdpCommunicationsInfoReflect>> mdpCommunicationsInfoMap=this.mdpCommunicationInfoService.queryMdpCommuicationInfoReflect(mdpCommunicationsInfoReflect);
            if (mdpCommunicationsInfoMap != null) {
                return ResponseEntity.ok(mdpCommunicationsInfoMap);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(value = "phone-commuication-single", method = RequestMethod.GET)
    public ResponseEntity<Map<String,List<MdpCommunicationsInfo>>> getCommunicationsInfoSingle(MdpCommunicationsInfo mdpCommunicationsInfo){
        try {
            Map<String,List<MdpCommunicationsInfo>> mdpCommunicationsInfoMap=this.mdpCommunicationInfoService.queryMdpCommuicationInfo(mdpCommunicationsInfo);
            if (mdpCommunicationsInfoMap != null) {
                    return ResponseEntity.ok(mdpCommunicationsInfoMap);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @RequestMapping(value = "accommodationInfo", method = RequestMethod.GET)
    public ResponseEntity<List<MdpAccommodationInfo>> getMdpAccommodationInfo(MdpAccommodationInfo mdpAccommodationInfo){
        try {
            List<MdpAccommodationInfo> mdpAccommodationInfos=this.mdpAccommodationInfoService.queryMdpAccommodationInfo(mdpAccommodationInfo);
            if (mdpAccommodationInfos != null) {
                return ResponseEntity.ok(mdpAccommodationInfos);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @RequestMapping(value = "most-long-week", method = RequestMethod.GET)
    public ResponseEntity<List<MdpCommunicationMostLongTimePerWeek>> getMdpCommunicationMostLongTimePerWeek(MdpCommunicationMostLongTimePerWeek mdpCommunicationMostLongTimePerWeek){
        try {
            List<MdpCommunicationMostLongTimePerWeek> mdpCommunicationMostLongTimePerWeeks=this.mdpCommunicationInfoService.queryMdpCommuicationMostLongTimePerWeek(mdpCommunicationMostLongTimePerWeek);
            if (mdpCommunicationMostLongTimePerWeeks != null) {
                return ResponseEntity.ok(mdpCommunicationMostLongTimePerWeeks);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @RequestMapping(value = "most-freq-per-week", method = RequestMethod.GET)
    public ResponseEntity<List<MdpCommunicationMostFrequentlyPerWeek>> getMdpCommunicationMostFrequentlyPerWeek(MdpCommunicationMostFrequentlyPerWeek mdpCommunicationMostFrequentlyPerWeek){
        try {
            List<MdpCommunicationMostFrequentlyPerWeek> mdpCommunicationMostFrequentlyPerWeeks=this.mdpCommunicationInfoService.queryMdpCommunicationMostFrequentlyPerWeek(mdpCommunicationMostFrequentlyPerWeek);
            if (mdpCommunicationMostFrequentlyPerWeeks != null) {
                return ResponseEntity.ok(mdpCommunicationMostFrequentlyPerWeeks);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(value = "most-freq-per-month", method = RequestMethod.GET)
    public ResponseEntity<List<MdpCommunicationMostFrequentlyPerMonth>> getMdpCommunicationMostFrequentlyPerMonth(MdpCommunicationMostFrequentlyPerMonth mdpCommunicationMostFrequentlyPerMonth){
        try {
            List<MdpCommunicationMostFrequentlyPerMonth> mdpCommunicationMostFrequentlyPerMonths=this.mdpCommunicationInfoService.queryMdpCommunicationMostFrequentlyPerMonth(mdpCommunicationMostFrequentlyPerMonth);
            if (mdpCommunicationMostFrequentlyPerMonths != null) {
                return ResponseEntity.ok(mdpCommunicationMostFrequentlyPerMonths);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping(value = "flight-traveling", method = RequestMethod.GET)
    public ResponseEntity<List<MdpFlightTravelingInfo>> getMdpFlightTravelingInfo(MdpFlightTravelingInfo mdpFlightTravelingInfo){
        try {
            List<MdpFlightTravelingInfo> mdpFlightTravelingInfos=this.mdpFlightTravelingInfoService.queryMdpFlightTravelingInfo(mdpFlightTravelingInfo);
            if (mdpFlightTravelingInfos != null) {
                return ResponseEntity.ok(mdpFlightTravelingInfos);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @RequestMapping(value = "internet", method = RequestMethod.GET)
    public ResponseEntity<List<MdpInternetInfo>> getMdpInternetInfo(MdpInternetInfo mdpInternetInfo){
        try {
            List<MdpInternetInfo> mdpInternetInfos=this.mdpInternetInfoService.queryMdpInternetInfo(mdpInternetInfo);
            if (mdpInternetInfos != null) {
                return ResponseEntity.ok(mdpInternetInfos);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

 /*   @RequestMapping(value = "internet", method = RequestMethod.GET)
    public ResponseEntity<List<MdpInternetInfo>> getMdpFlightTravelingInfo(MdpInternetInfo mdpInternetInfo){
        try {
            List<MdpInternetInfo> mdpInternetInfos=this.mdpInternetInfoService.queryMdpInternetInfo(mdpInternetInfo);
            if (mdpInternetInfos != null) {
                return ResponseEntity.ok(mdpInternetInfos);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }*/


    @RequestMapping(value = "train-traveling", method = RequestMethod.GET)
    public ResponseEntity<List<MdpTrainTravelingInfo>> getMdpTrainTravelingInfo(MdpTrainTravelingInfo mdpTrainTravelingInfo){
        try {
            List<MdpTrainTravelingInfo> mdpTrainTravelingInfos=this.mdpTrainTravelingInfoService.queryMdpTrainTravelingInfo(mdpTrainTravelingInfo);
            if (mdpTrainTravelingInfos != null) {
                return ResponseEntity.ok(mdpTrainTravelingInfos);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @RequestMapping(value="phone-communication-hour-count",method=RequestMethod.GET)
    public ResponseEntity<Map<String,List<MdpConnectionInfoHourCount>>> getCommuicationsInfoHourCount(MdpConnectionInfoHourCount mdpConnectionInfoHourCount){

        try {

            Map<String,List<MdpConnectionInfoHourCount>> mdpConnectionInfoCountMap=this.mdpConnectionInfoHourCountService.queryMdpConnectionInfoHourCount(mdpConnectionInfoHourCount);

            if(mdpConnectionInfoCountMap!=null){
                return ResponseEntity.ok(mdpConnectionInfoCountMap);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);



    }







}
