package com.hikvision.mdp.web.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by geting on 2017/1/24.
 */

@SuppressWarnings("all")
@Table(name = "mdp_connection_info_hour_count")
public class MdpConnectionInfoHourCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String cardId;
    private String callerNum;
    private String date;
    private String day;
    private String hour;
    private String connCount;
    private String type;

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

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCallerNum() {
        return callerNum;
    }

    public void setCallerNum(String callerNum) {
        this.callerNum = callerNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getConnCount() {
        return connCount;
    }

    public void setConnCount(String connCount) {
        this.connCount = connCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MdpConnectionInfoHourCount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                ", callerNum='" + callerNum + '\'' +
                ", date='" + date + '\'' +
                ", day='" + day + '\'' +
                ", hour='" + hour + '\'' +
                ", connCount='" + connCount + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
