package com.hikvision.mdp.web.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by geting on 2017/2/10.
 */

@SuppressWarnings("all")
@Table(name = "mdp_flight_traveling_info")
public class MdpFlightTravelingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String flight_id;
    private String name_spell;
    private String id_type;
    private String id_num;
    private String sex;
    private String age;
    private String nation;
    private String flightFrom;
    private String flightTo;
    private String flightSeat;
    private String flightCheckinDate;
    private String flightCheckinTime;
    private String flightBuyticketDate;
    private String flightBuyticketTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    public String getName_spell() {
        return name_spell;
    }

    public void setName_spell(String name_spell) {
        this.name_spell = name_spell;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getFlightFrom() {
        return flightFrom;
    }

    public void setFlightFrom(String flightFrom) {
        this.flightFrom = flightFrom;
    }

    public String getFlightTo() {
        return flightTo;
    }

    public void setFlightTo(String flightTo) {
        this.flightTo = flightTo;
    }

    public String getFlightSeat() {
        return flightSeat;
    }

    public void setFlightSeat(String flightSeat) {
        this.flightSeat = flightSeat;
    }

    public String getFlightCheckinDate() {
        return flightCheckinDate;
    }

    public void setFlightCheckinDate(String flightCheckinDate) {
        this.flightCheckinDate = flightCheckinDate;
    }

    public String getFlightCheckinTime() {
        return flightCheckinTime;
    }

    public void setFlightCheckinTime(String flightCheckinTime) {
        this.flightCheckinTime = flightCheckinTime;
    }

    public String getFlightBuyticketDate() {
        return flightBuyticketDate;
    }

    public void setFlightBuyticketDate(String flightBuyticketDate) {
        this.flightBuyticketDate = flightBuyticketDate;
    }

    public String getFlightBuyticketTime() {
        return flightBuyticketTime;
    }

    public void setFlightBuyticketTime(String flightBuyticketTime) {
        this.flightBuyticketTime = flightBuyticketTime;
    }

    @Override
    public String toString() {
        return "MdpFlightTravelingInfo{" +
                "id='" + id + '\'' +
                ", flight_id='" + flight_id + '\'' +
                ", name_spell='" + name_spell + '\'' +
                ", id_type='" + id_type + '\'' +
                ", id_num='" + id_num + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", nation='" + nation + '\'' +
                ", flightFrom='" + flightFrom + '\'' +
                ", flightTo='" + flightTo + '\'' +
                ", flightSeat='" + flightSeat + '\'' +
                ", flightCheckinDate='" + flightCheckinDate + '\'' +
                ", flightCheckinTime='" + flightCheckinTime + '\'' +
                ", flightBuyticketDate='" + flightBuyticketDate + '\'' +
                ", flightBuyticketTime='" + flightBuyticketTime + '\'' +
                '}';
    }
}
