package com.hikvision.mdp.web.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by geting on 2017/2/10.
 */

@SuppressWarnings("all")
@Table(name = "mdp_train_traveling_info")
public class MdpTrainTravelingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String trainId;
    private String nameSpell;
    private String idType;
    private String idNum;
    private String sex;
    private String trainFrom;
    private String trainTo;
    private String trainSeat;
    private String trainCheckinDate;
    private String trainCheckinTime;
    private String trainBuyticketDate;
    private String trainBuyticketTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getNameSpell() {
        return nameSpell;
    }

    public void setNameSpell(String nameSpell) {
        this.nameSpell = nameSpell;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTrainFrom() {
        return trainFrom;
    }

    public void setTrainFrom(String trainFrom) {
        this.trainFrom = trainFrom;
    }

    public String getTrainTo() {
        return trainTo;
    }

    public void setTrainTo(String trainTo) {
        this.trainTo = trainTo;
    }

    public String getTrainSeat() {
        return trainSeat;
    }

    public void setTrainSeat(String trainSeat) {
        this.trainSeat = trainSeat;
    }

    public String getTrainCheckinDate() {
        return trainCheckinDate;
    }

    public void setTrainCheckinDate(String trainCheckinDate) {
        this.trainCheckinDate = trainCheckinDate;
    }

    public String getTrainCheckinTime() {
        return trainCheckinTime;
    }

    public void setTrainCheckinTime(String trainCheckinTime) {
        this.trainCheckinTime = trainCheckinTime;
    }

    public String getTrainBuyticketDate() {
        return trainBuyticketDate;
    }

    public void setTrainBuyticketDate(String trainBuyticketDate) {
        this.trainBuyticketDate = trainBuyticketDate;
    }

    public String getTrainBuyticketTime() {
        return trainBuyticketTime;
    }

    public void setTrainBuyticketTime(String trainBuyticketTime) {
        this.trainBuyticketTime = trainBuyticketTime;
    }

    @Override
    public String toString() {
        return "MdpTrainTravelingInfo{" +
                "id='" + id + '\'' +
                ", trainId='" + trainId + '\'' +
                ", nameSpell='" + nameSpell + '\'' +
                ", idType='" + idType + '\'' +
                ", idNum='" + idNum + '\'' +
                ", sex='" + sex + '\'' +
                ", trainFrom='" + trainFrom + '\'' +
                ", trainTo='" + trainTo + '\'' +
                ", trainSeat='" + trainSeat + '\'' +
                ", trainCheckinDate='" + trainCheckinDate + '\'' +
                ", trainCheckinTime='" + trainCheckinTime + '\'' +
                ", trainBuyticketDate='" + trainBuyticketDate + '\'' +
                ", trainBuyticketTime='" + trainBuyticketTime + '\'' +
                '}';
    }
}
