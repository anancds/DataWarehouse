package com.hikvision.mdp.web.pojo;

import com.hikvision.mdp.web.pojo.MdpCommunicationsInfo;
import com.hikvision.mdp.web.pojo.MdpDataObjectReflect;

import javax.persistence.Table;

/**
 * Created by geting on 2017/2/13.
 */

@SuppressWarnings("all")
@Table(name = "view_mdp_communication_info")
public class MdpCommunicationsInfoReflect extends MdpCommunicationsInfo{

   private String dataId;
   private String objectId;
   private String tableName;

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
        return "MdpCommunicationsInfoReflect{" +
                "dataId='" + dataId + '\'' +
                ", objectId='" + objectId + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
