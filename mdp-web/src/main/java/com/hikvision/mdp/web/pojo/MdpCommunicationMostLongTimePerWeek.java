package com.hikvision.mdp.web.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by geting on 2017/1/24.
 */
@SuppressWarnings("all")
@Table(name = "mdp_communication_most_long_per_week")
public class MdpCommunicationMostLongTimePerWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String callerNum;
    private String busType;
    private String calledNum;
    private String connCount;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCallerNum() {
        return callerNum;
    }

    public void setCallerNum(String callerNum) {
        this.callerNum = callerNum;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getCalledNum() {
        return calledNum;
    }

    public void setCalledNum(String calledNum) {
        this.calledNum = calledNum;
    }

    public String getConnCount() {
        return connCount;
    }

    public void setConnCount(String connCount) {
        this.connCount = connCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MdpCommunicationMostFrequentlyPerMonth{" +
                "id='" + id + '\'' +
                ", callerNum='" + callerNum + '\'' +
                ", busType='" + busType + '\'' +
                ", calledNum='" + calledNum + '\'' +
                ", connCount='" + connCount + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
