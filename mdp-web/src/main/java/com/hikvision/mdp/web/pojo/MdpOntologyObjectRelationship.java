package com.hikvision.mdp.web.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by geting on 2017/2/8.
 */

@SuppressWarnings("all")
@Table(name = "mdp_ontology_object_properties")
public class MdpOntologyObjectRelationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private String relId;

    @JsonProperty("source")
    private String relObjId1;
    @JsonProperty("target")
    private String relObjId2;
    @JsonIgnore
    private String relValue;
    @JsonProperty("name")
    private String relDesc;
    @JsonIgnore
    private String relDirection;

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public String getRelObjId1() {
        return relObjId1;
    }

    public void setRelObjId1(String relObjId1) {
        this.relObjId1 = relObjId1;
    }

    public String getRelObjId2() {
        return relObjId2;
    }

    public void setRelObjId2(String relObjId2) {
        this.relObjId2 = relObjId2;
    }

    public String getRelValue() {
        return relValue;
    }

    public void setRelValue(String relValue) {
        this.relValue = relValue;
    }

    public String getRelDesc() {
        return relDesc;
    }

    public void setRelDesc(String relDesc) {
        this.relDesc = relDesc;
    }

    public String getRelDirection() {
        return relDirection;
    }

    public void setRelDirection(String relDirection) {
        this.relDirection = relDirection;
    }

    @Override
    public String toString() {
        return "MdpOntologyObjectRelationship{" +
                "relId='" + relId + '\'' +
                ", relObjId1='" + relObjId1 + '\'' +
                ", relObjId2='" + relObjId2 + '\'' +
                ", relValue='" + relValue + '\'' +
                ", relDesc='" + relDesc + '\'' +
                ", relDirection='" + relDirection + '\'' +
                '}';
    }
}
