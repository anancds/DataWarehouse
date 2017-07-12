package com.hikvision.mdp.web.mapper;

import com.hikvision.mdp.web.pojo.MdpOntologyObjectModel;
import com.hikvision.mdp.web.pojo.MdpOntologyObjectRelationship;
import com.hikvision.mdp.web.pojo.MdpOntologyObjectType;

import java.util.List;

/**
 * Created by geting on 2017/2/8.
 */
public interface MdpTimeSpaceDynamicOnontologyMapper {

        public List<MdpOntologyObjectType> queryAllUsedMdpOntologyObjectType();

        public List<MdpOntologyObjectModel> queryAllMdpOntologyObject();

        public List<MdpOntologyObjectRelationship> queryAllObjectsRelationShip();

        public List<MdpOntologyObjectRelationship> queryObjectsRelationShipByIds (List<String> ids);

        public List<MdpOntologyObjectModel> queryMdpOntologyObjectByIds(List<String> ids);

        public List<MdpOntologyObjectType> queryUsedMdpOntologyObjectTypeByObjIds(List<String> ids);
}
