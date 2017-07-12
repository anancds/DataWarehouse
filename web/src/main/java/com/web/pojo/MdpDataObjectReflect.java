package com.hikvision.mdp.web.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by geting on 2017/2/13.
 */

@SuppressWarnings("all")
@Table(name = "mdp_data_object_reflect")
public class MdpDataObjectReflect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String dataId;
    private String objectId;
    private String tableName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "MdpDataObjectReflect{" +
                "id='" + id + '\'' +
                ", dataId='" + dataId + '\'' +
                ", objectId='" + objectId + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
