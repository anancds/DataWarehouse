package com.hikvision.mdp.web.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by geting on 2017/2/7.
 */


@SuppressWarnings("all")
@Table(name = "mdp_connection_info_hour_count")
public class MdpOntologyObjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String typeId;
    private String typeValue;
    private String typeDesc;
    private String typeParentId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getTypeParentId() {
        return typeParentId;
    }

    public void setTypeParentId(String typeParentId) {
        this.typeParentId = typeParentId;
    }

    @Override
    public String toString() {
        return "MdpOntologyObjectType{" +
                "typeId='" + typeId + '\'' +
                ", typeValue='" + typeValue + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                ", typeParentId='" + typeParentId + '\'' +
                '}';
    }
}
