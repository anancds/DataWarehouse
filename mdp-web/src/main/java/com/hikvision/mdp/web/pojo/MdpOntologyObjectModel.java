package com.hikvision.mdp.web.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by geting on 2017/2/8.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MdpOntologyObjectModel {

    @JsonProperty("name")
    private String objId;
    @JsonProperty("label")
    private String objName;
    @JsonIgnore
    private String objType;
    @JsonProperty("obj_prop")
    private List<MdpOntologyObjectProperties> objeProperties;


    @JsonIgnore
    private String symbol;

    private String value;

    private String category;

    private String categoryValue;


    public String getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getSymbolSize() {

        return "[200,200]";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<MdpOntologyObjectProperties> getObjeProperties() {
        return objeProperties;
    }

    public void setObjeProperties(List<MdpOntologyObjectProperties> objeProperties) {
        this.objeProperties = objeProperties;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "MdpOntologyObjectModel{" +
                "objId='" + objId + '\'' +
                ", objName='" + objName + '\'' +
                ", objType='" + objType + '\'' +
                ", objeProperties=" + objeProperties +
                ", category='" + category + '\'' +
                '}';
    }
}
