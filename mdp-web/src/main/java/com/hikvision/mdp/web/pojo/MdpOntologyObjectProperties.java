package com.hikvision.mdp.web.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by geting on 2017/2/8.
 */

@SuppressWarnings("all")
@Table(name = "mdp_ontology_object_properties")
public class MdpOntologyObjectProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String propId;
    private String propName;
    private String propValue;
    private String propObjId;


    public String getPropId() {
        return propId;
    }

    public void setPropId(String propId) {
        this.propId = propId;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public String getPropObjId() {
        return propObjId;
    }

    public void setPropObjId(String propObjId) {
        this.propObjId = propObjId;
    }

    @Override
    public String toString() {
        return "MdpOntologyObjectProperties{" +
                "propId='" + propId + '\'' +
                ", propName='" + propName + '\'' +
                ", propValue='" + propValue + '\'' +
                ", propObjId='" + propObjId + '\'' +
                '}';
    }
}
