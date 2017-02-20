package com.hikvision.mdp.web.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by geting on 2017/2/10.
 */
@SuppressWarnings("all")
@Table(name = "mdp_internet_info")
public class MdpInternetInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String id_type;
    private String id_num;
    private String id_issuing_agency;
    private String  nationality;
    private String businessPremisesCode;
    private String businessPremisesName;
    private String Location;
    private String startDate;
    private String startTime;
    private String terminalCode;
    private String endTime;
    private String loginTime;
    private String createTime;
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getId_issuing_agency() {
        return id_issuing_agency;
    }

    public void setId_issuing_agency(String id_issuing_agency) {
        this.id_issuing_agency = id_issuing_agency;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    public String getBusinessPremisesCode() {
        return businessPremisesCode;
    }

    public void setBusinessPremisesCode(String businessPremisesCode) {
        this.businessPremisesCode = businessPremisesCode;
    }

    public String getBusinessPremisesName() {
        return businessPremisesName;
    }

    public void setBusinessPremisesName(String businessPremisesName) {
        this.businessPremisesName = businessPremisesName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MdpInternetInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", id_type='" + id_type + '\'' +
                ", id_num='" + id_num + '\'' +
                ", id_issuing_agency='" + id_issuing_agency + '\'' +
                ", nationality='" + nationality + '\'' +
                ", businessPremisesCode='" + businessPremisesCode + '\'' +
                ", businessPremisesName='" + businessPremisesName + '\'' +
                ", Location='" + Location + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", terminalCode='" + terminalCode + '\'' +
                ", endTime='" + endTime + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
