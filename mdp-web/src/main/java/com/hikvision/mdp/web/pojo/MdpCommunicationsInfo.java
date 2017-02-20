package com.hikvision.mdp.web.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by geting on 2017/1/19.
 */
@SuppressWarnings("all")
@Table(name = "mdp_communications_info")
public class MdpCommunicationsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String telOperatorType;
    private String  businessType;
    private String startTime;
    private String callerNum;
    private String callerNumVest;
    private String cardId;
    private String deviceId;
    private String calledNum;
    private String callDuration;
    private String calledNumVest;
    private String callType;
    private String lac;
    private String cid;
    private String callerNumStation;
    private String msc;
    private String city;
    private String thirdPartyNum;
    private String thirdPartyVest;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelOperatorType() {
        return telOperatorType;
    }

    public void setTelOperatorType(String telOperatorType) {
        this.telOperatorType = telOperatorType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCallerNum() {
        return callerNum;
    }

    public void setCallerNum(String callerNum) {
        this.callerNum = callerNum;
    }

    public String getCallerNumVest() {
        return callerNumVest;
    }

    public void setCallerNumVest(String callerNumVest) {
        this.callerNumVest = callerNumVest;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCalledNum() {
        return calledNum;
    }

    public void setCalledNum(String calledNum) {
        this.calledNum = calledNum;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getCalledNumVest() {
        return calledNumVest;
    }

    public void setCalledNumVest(String calledNumVest) {
        this.calledNumVest = calledNumVest;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCallerNumStation() {
        return callerNumStation;
    }

    public void setCallerNumStation(String callerNumStation) {
        this.callerNumStation = callerNumStation;
    }

    public String getMsc() {
        return msc;
    }

    public void setMsc(String msc) {
        this.msc = msc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getThirdPartyNum() {
        return thirdPartyNum;
    }

    public void setThirdPartyNum(String thirdPartyNum) {
        this.thirdPartyNum = thirdPartyNum;
    }

    public String getThirdPartyVest() {
        return thirdPartyVest;
    }

    public void setThirdPartyVest(String thirdPartyVest) {
        this.thirdPartyVest = thirdPartyVest;
    }

    @Override
    public String toString() {
        return "MdpCommunicationsInfo{" +
                "id='" + id + '\'' +
                ", telOperatorType='" + telOperatorType + '\'' +
                ", businessType='" + businessType + '\'' +
                ", startTime='" + startTime + '\'' +
                ", callerNum='" + callerNum + '\'' +
                ", callerNumVest='" + callerNumVest + '\'' +
                ", cardId='" + cardId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", calledNum='" + calledNum + '\'' +
                ", callDuration='" + callDuration + '\'' +
                ", calledNumVest='" + calledNumVest + '\'' +
                ", callType='" + callType + '\'' +
                ", lac='" + lac + '\'' +
                ", cid='" + cid + '\'' +
                ", callerNumStation='" + callerNumStation + '\'' +
                ", msc='" + msc + '\'' +
                ", city='" + city + '\'' +
                ", thirdPartyNum='" + thirdPartyNum + '\'' +
                ", thirdPartyVest='" + thirdPartyVest + '\'' +
                '}';
    }
}
