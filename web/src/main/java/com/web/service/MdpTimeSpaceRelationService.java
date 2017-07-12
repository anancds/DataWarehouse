package com.hikvision.mdp.web.service;

import com.hikvision.mdp.web.bean.MdpCategoryObject;
import com.hikvision.mdp.web.bean.MdpTimeSpaceDynamicOnontology;
import com.hikvision.mdp.web.mapper.MdpTimeSpaceDynamicOnontologyMapper;
import com.hikvision.mdp.web.pojo.MdpOntologyObjectModel;
import com.hikvision.mdp.web.pojo.MdpOntologyObjectProperties;
import com.hikvision.mdp.web.pojo.MdpOntologyObjectRelationship;
import com.hikvision.mdp.web.pojo.MdpOntologyObjectType;
import org.aspectj.asm.internal.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by geting on 2017/2/8.
 */

@SuppressWarnings("all")
@Service
public class MdpTimeSpaceRelationService {

    @Autowired
    private MdpTimeSpaceDynamicOnontologyMapper mdpTimeSpaceDynamicOnontologyMapper;

    /**
     * 查询全量对象模型类型信息
     * @return
     */
    public List<MdpOntologyObjectType> queryAllUsedMdpOntologyObjectType() {
        List<MdpOntologyObjectType> mdpOntologyObjectTypes = this.mdpTimeSpaceDynamicOnontologyMapper.queryAllUsedMdpOntologyObjectType();
        if (mdpOntologyObjectTypes == null || mdpOntologyObjectTypes.size() == 0) {
            return null;
        }
        return mdpOntologyObjectTypes;
    }


    /**
     * 通过对象模型id 筛选对象模型类型信息
     * @param ids
     * @return
     */
    public List<MdpOntologyObjectType> queryUsedMdpOntologyObjectTypeByObjIds(List<String> ids) {
        List<MdpOntologyObjectType> mdpOntologyObjectTypes = this.mdpTimeSpaceDynamicOnontologyMapper.queryUsedMdpOntologyObjectTypeByObjIds(ids);
        if (mdpOntologyObjectTypes == null || mdpOntologyObjectTypes.size() == 0) {
            return null;
        }
        return mdpOntologyObjectTypes;
    }


    /**
     * 查询全量对象模型信息
     * @return
     */

    public List<MdpOntologyObjectModel> queryAllUsedMdpOntologyObject() {
        List<MdpOntologyObjectModel> mdpOntologyObjectModels = this.mdpTimeSpaceDynamicOnontologyMapper.queryAllMdpOntologyObject();
        if (mdpOntologyObjectModels == null || mdpOntologyObjectModels.size() == 0) {
            return null;
        }
        return mdpOntologyObjectModels;
    }

    public List<MdpOntologyObjectModel> queryMdpOntologyObjectByIds(List<String> ids) {
        List<MdpOntologyObjectModel> mdpOntologyObjectModels = this.mdpTimeSpaceDynamicOnontologyMapper.queryMdpOntologyObjectByIds(ids);
        if (mdpOntologyObjectModels == null || mdpOntologyObjectModels.size() == 0) {
            return null;
        }
        return mdpOntologyObjectModels;
    }



    public List<MdpOntologyObjectRelationship> queryAllObjectsRelationShip() {
        List<MdpOntologyObjectRelationship> mdpOntologyObjectRelationships = this.mdpTimeSpaceDynamicOnontologyMapper.queryAllObjectsRelationShip();
        if (mdpOntologyObjectRelationships == null || mdpOntologyObjectRelationships.size() == 0) {
            return null;
        }
        return mdpOntologyObjectRelationships;
    }

    /**
     * 生成动态本体数据
     * @param mdpOntologyObjectTypes
     * @param mdpOntologyObjectModels
     * @param mdpOntologyObjectRelationships
     * @return MdpTimeSpaceDynamicOnontology
     */
    private  MdpTimeSpaceDynamicOnontology genrateDynamicOnontology(List<MdpOntologyObjectType> mdpOntologyObjectTypes, List<MdpOntologyObjectModel> mdpOntologyObjectModels,List<MdpOntologyObjectRelationship> mdpOntologyObjectRelationships){
        if (mdpOntologyObjectTypes == null || mdpOntologyObjectTypes.size() == 0 || mdpOntologyObjectModels == null || mdpOntologyObjectModels.size() == 0)
            return null;
        List<String> legendData = new ArrayList<String>();
        List<MdpCategoryObject> categoriesData = new ArrayList<MdpCategoryObject>();
        Map<String, String> mdpOntologyObjectTypeMap = new HashMap<String, String>();

        for (MdpOntologyObjectType mdpOntologyObjectType : mdpOntologyObjectTypes) {
            //
            legendData.add(mdpOntologyObjectType.getTypeDesc());
            //
            categoriesData.add(new MdpCategoryObject(mdpOntologyObjectType.getTypeDesc()));
            //
            mdpOntologyObjectTypeMap.put(mdpOntologyObjectType.getTypeValue(), mdpOntologyObjectType.getTypeDesc());
        }

        for (MdpOntologyObjectModel mdpOntologyObjectModel : mdpOntologyObjectModels) {
            mdpOntologyObjectModel.setCategory(legendData.indexOf(mdpOntologyObjectTypeMap.get(mdpOntologyObjectModel.getObjType())) + "");
            mdpOntologyObjectModel.setCategoryValue(mdpOntologyObjectTypeMap.get(mdpOntologyObjectModel.getObjType()));
            for (MdpOntologyObjectProperties mdpOntologyObjectProperties : mdpOntologyObjectModel.getObjeProperties()) {
                if ("img1".equals(mdpOntologyObjectProperties.getPropName()))
                    mdpOntologyObjectModel.setSymbol(mdpOntologyObjectProperties.getPropValue());
            }
        }

       /* mdpOntologyObjectModels.get(0).setValue("50");*/

        return new MdpTimeSpaceDynamicOnontology(legendData, categoriesData, mdpOntologyObjectModels, mdpOntologyObjectRelationships);
    }

    /**
     * 查询全量动态本体信息
     * @return
     */
    public MdpTimeSpaceDynamicOnontology queryfullDoseDynamicOnontology() {
        List<MdpOntologyObjectType> mdpOntologyObjectTypes = this.queryAllUsedMdpOntologyObjectType();
        List<MdpOntologyObjectModel> mdpOntologyObjectModels = this.queryAllUsedMdpOntologyObject();
        List<MdpOntologyObjectRelationship> mdpOntologyObjectRelationships = this.queryAllObjectsRelationShip();

        return this.genrateDynamicOnontology(mdpOntologyObjectTypes,mdpOntologyObjectModels,mdpOntologyObjectRelationships);

        /*if (mdpOntologyObjectTypes == null || mdpOntologyObjectTypes.size() == 0 || mdpOntologyObjectModels == null || mdpOntologyObjectModels.size() == 0)
            return null;
        List<String> legendData = new ArrayList<String>();
        List<MdpCategoryObject> categoriesData = new ArrayList<MdpCategoryObject>();
        Map<String, String> mdpOntologyObjectTypeMap = new HashMap<String, String>();

        for (MdpOntologyObjectType mdpOntologyObjectType : mdpOntologyObjectTypes) {
            //
            legendData.add(mdpOntologyObjectType.getTypeDesc());
            //
            categoriesData.add(new MdpCategoryObject(mdpOntologyObjectType.getTypeDesc()));
            //
            mdpOntologyObjectTypeMap.put(mdpOntologyObjectType.getTypeValue(), mdpOntologyObjectType.getTypeDesc());
        }

        for (MdpOntologyObjectModel mdpOntologyObjectModel : mdpOntologyObjectModels) {
            mdpOntologyObjectModel.setCategory(legendData.indexOf(mdpOntologyObjectTypeMap.get(mdpOntologyObjectModel.getObjType())) + "");
            for (MdpOntologyObjectProperties mdpOntologyObjectProperties : mdpOntologyObjectModel.getObjeProperties()) {
                if ("img1".equals(mdpOntologyObjectProperties.getPropName()))
                    mdpOntologyObjectModel.setSymbol(mdpOntologyObjectProperties.getPropValue());
            }
        }

        mdpOntologyObjectModels.get(0).setValue("100");

        return new MdpTimeSpaceDynamicOnontology(legendData, categoriesData, mdpOntologyObjectModels, mdpOntologyObjectRelationships);*/

    }

    /**
     * 通过对象模型ID信息查询对象模型及关系信息
     * @param ids
     * @return
     */
    public List<MdpOntologyObjectRelationship> queryObjectsRelationShipByIds(List<String> ids) {
        List<MdpOntologyObjectRelationship> mdpOntologyObjectRelationships = this.mdpTimeSpaceDynamicOnontologyMapper.queryObjectsRelationShipByIds(ids);
        if (mdpOntologyObjectRelationships == null || mdpOntologyObjectRelationships.size() == 0) {
            return null;
        }
        return mdpOntologyObjectRelationships;
    }

    private  List<MdpOntologyObjectRelationship>  genrateMdpOntologyObjectRelationships(Map<String, MdpOntologyObjectRelationship> mdpOntologyObjectRelationshipsMap){

        List<MdpOntologyObjectRelationship> mdpOntologyObjectRelationship=new ArrayList<MdpOntologyObjectRelationship>();
        for (MdpOntologyObjectRelationship value : mdpOntologyObjectRelationshipsMap.values()) {

            mdpOntologyObjectRelationship.add(value);
        }



        return mdpOntologyObjectRelationship;
    }


    /**
     * 通过对象模型IDs及继承层级关系信息查询对象模型及关系信息
     * @param ids
     * @param hierarchy
     * @return
     */
    public MdpTimeSpaceDynamicOnontology queryDynamicOnontologyByIdsAndHierarchy(String ids, Integer hierarchy) {
        if (ids == null)
            return null;
        if (hierarchy == null || hierarchy == 0)
            hierarchy = 1;

        String [] arr=ids.split(",");
        List<String> idsTemp=new ArrayList<String>();
        for(String item:arr){
            idsTemp.add(item);
        }

       /* List<String> idsTemp = Arrays.asList(ids.split(","));*/
        List<String> mdpOntologyObjectIds = new ArrayList<String>();
        Map<String, MdpOntologyObjectRelationship> mdpOntologyObjectRelationshipsMap = new HashMap<String, MdpOntologyObjectRelationship>();

        mdpOntologyObjectIds.addAll(idsTemp);
        for (int i = 0; i < hierarchy; i++) {
            //合并ids
           /* mdpOntologyObjectIds.addAll(idsTemp);*/
            //通过ids查找到与object相关联的的关系
            if(idsTemp==null||idsTemp.size()==0) {
                break;
            }
            List<MdpOntologyObjectRelationship> mdpOntologyObjectRelationships =this.queryObjectsRelationShipByIds(idsTemp);


            idsTemp.clear();
            //获得关系id_id
            for (MdpOntologyObjectRelationship mdpOntologyObjectRelationship : mdpOntologyObjectRelationships) {
                String id1 = mdpOntologyObjectRelationship.getRelObjId1();
                String id2 = mdpOntologyObjectRelationship.getRelObjId2();


                if (!mdpOntologyObjectIds.contains(id1)) {
                    idsTemp.add(id1);
                }

                if (!mdpOntologyObjectIds.contains(id2)) {
                    idsTemp.add(id2);
                }

                if (!mdpOntologyObjectRelationshipsMap.containsKey(id1 + "_" + id2)) {
                    mdpOntologyObjectRelationshipsMap.put(id1 + "_" + id2, mdpOntologyObjectRelationship);
                }




            }
            mdpOntologyObjectIds.addAll(idsTemp);
        }



        List<MdpOntologyObjectType> mdpOntologyObjectTypes = this.queryUsedMdpOntologyObjectTypeByObjIds(mdpOntologyObjectIds);
        List<MdpOntologyObjectModel> mdpOntologyObjectModels = this.queryMdpOntologyObjectByIds(mdpOntologyObjectIds);
        List<MdpOntologyObjectRelationship> mdpOntologyObjectRelationships = this.genrateMdpOntologyObjectRelationships(mdpOntologyObjectRelationshipsMap);


        return this.genrateDynamicOnontology(mdpOntologyObjectTypes,mdpOntologyObjectModels,mdpOntologyObjectRelationships);
    }


}
