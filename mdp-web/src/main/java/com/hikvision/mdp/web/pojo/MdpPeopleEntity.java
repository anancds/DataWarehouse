package com.hikvision.mdp.web.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by geting on 2017/2/23.
 */
@SuppressWarnings("all")
@Table(name = "mdp_people_entity")
public class MdpPeopleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String nameSpell;
    private String cardId;
    private String phoneNum;
    private String sex;
    private String ethnicity;
    private String birDay;
    private String marriage;
    private String education;
    private String job;
    private String adress;
    private String state;

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

    public String getNameSpell() {
        return nameSpell;
    }

    public void setNameSpell(String nameSpell) {
        this.nameSpell = nameSpell;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getBirDay() {
        return birDay;
    }

    public void setBirDay(String birDay) {
        this.birDay = birDay;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "MdpPeopleEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nameSpell='" + nameSpell + '\'' +
                ", cardId='" + cardId + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", sex='" + sex + '\'' +
                ", ethnicity='" + ethnicity + '\'' +
                ", birDay='" + birDay + '\'' +
                ", marriage='" + marriage + '\'' +
                ", education='" + education + '\'' +
                ", job='" + job + '\'' +
                ", adress='" + adress + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
