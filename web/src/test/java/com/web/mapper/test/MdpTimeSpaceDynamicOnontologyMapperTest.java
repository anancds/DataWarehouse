package com.hikvision.mdp.web.mapper.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.entity.Example;
import com.hikvision.mdp.web.mapper.UserMapper;
import com.hikvision.mdp.web.pojo.*;
import com.hikvision.mdp.web.bean.MdpTimeSpaceDynamicOnontology;
import com.hikvision.mdp.web.mapper.MdpCommunicationMapper;
import com.hikvision.mdp.web.service.MdpCommunicationInfoService;
import com.hikvision.mdp.web.service.MdpPeopleEntityService;
import com.hikvision.mdp.web.service.MdpTimeSpaceRelationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by geting on 2017/2/8.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:spring/applicationContext*.xml")
@SuppressWarnings("all")
public class MdpTimeSpaceDynamicOnontologyMapperTest {

    @Autowired
    private MdpTimeSpaceRelationService mdpTimeSpaceRelationService;
    @Autowired
    private MdpCommunicationMapper mdpCommunicationMapper;

    @Autowired
    private MdpCommunicationInfoService mdpCommunicationInfoService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private MdpPeopleEntityService mdpPeopleEntityService;

    @Test
    public void testQueryAllRelationShip(){
        List<MdpOntologyObjectType> mdpOntologyObjectTypes= mdpTimeSpaceRelationService.queryAllUsedMdpOntologyObjectType();
        System.out.println(mdpOntologyObjectTypes);
    }


    @Test
    public void testQueryAllRelationObject(){
        List<MdpOntologyObjectModel> mdpOntologyObjectModels=mdpTimeSpaceRelationService.queryAllUsedMdpOntologyObject();
        System.out.print(mdpOntologyObjectModels);
    }


    @Test
    public void testQueryAllObjectsRelationShip(){
        List<MdpOntologyObjectRelationship> mdpOntologyObjectRelationships=this.mdpTimeSpaceRelationService.queryAllObjectsRelationShip();
        System.out.println(mdpOntologyObjectRelationships);
    }

    @Test
    public void testMdpTimeSpaceDynamicOnontology() throws JsonProcessingException {
        MdpTimeSpaceDynamicOnontology mdpTimeSpaceDynamicOnontology =this.mdpTimeSpaceRelationService.queryfullDoseDynamicOnontology();
        System.out.println(MAPPER.writeValueAsString(mdpTimeSpaceDynamicOnontology));
    }

    @Test
    public void testQueryObjectsRelationShipByIds(){
        List<String> lists=new ArrayList<String>();
        lists.add("1");
        lists.add("2");
        List<MdpOntologyObjectRelationship> mdpOntologyObjectRelationships=this.mdpTimeSpaceRelationService.queryObjectsRelationShipByIds(lists);
        System.out.println(mdpOntologyObjectRelationships);
    }

    @Test
    public void testSet(){
        Set<String> strings=new HashSet<String>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("2");
        System.out.println(strings.contains("2"));
        System.out.println(strings);
    }

    @Test
    public void testQueryOnontologyObjectByIds() throws JsonProcessingException {
        List<String> lists=new ArrayList<String>();
        lists.add("1");
        lists.add("2");
        List<MdpOntologyObjectModel> mdpOntologyObjectModels=this.mdpTimeSpaceRelationService.queryMdpOntologyObjectByIds(lists);
        System.out.println(MAPPER.writeValueAsString(mdpOntologyObjectModels));
    }


    @Test
    public void queryDynamicOnontologyByIdsAndHierarchy() throws JsonProcessingException {
        MdpTimeSpaceDynamicOnontology mdpTimeSpaceDynamicOnontologies=this.mdpTimeSpaceRelationService.queryDynamicOnontologyByIdsAndHierarchy("1",5);
        System.out.println(MAPPER.writeValueAsString(mdpTimeSpaceDynamicOnontologies));


    }

    @Test
    public void queryfullDoseDynamicOnontology ()throws JsonProcessingException{
        MdpTimeSpaceDynamicOnontology mdpTimeSpaceDynamicOnontologies=this.mdpTimeSpaceRelationService.queryfullDoseDynamicOnontology();
        System.out.println(MAPPER.writeValueAsString(mdpTimeSpaceDynamicOnontologies));
    }


    @Test
    public void testClear(){
        String [] arr=new String[2];
        arr[0]="1";
        arr[1]="2";
        List<String> list= Arrays.asList(arr);
       /* List<String> list=new ArrayList<String>();
        list.add("1");
        list.add("2");*/
        System.out.println(list);
        list.clear();
        System.out.println(list);
    }

    @Test
    public void queryMdpCommunicationsInfoReflect(){
       List<MdpCommunicationsInfoReflect> mdpCommunicationsInfoReflects=this.mdpCommunicationMapper.queryMdpCommunicationsInfoReflect(null);
       System.out.println(mdpCommunicationsInfoReflects);
    }

    @Test
    public void testMdpCommunicationInfoService(){
        Map<String,List<MdpCommunicationsInfoReflect>> maps=this.mdpCommunicationInfoService.queryMdpCommuicationInfoReflect(null);
        System.out.print(maps);
    }


    @Test
    public void testUser(){

        Example example= new Example(User.class);


        example.or().andLike("name","葛挺");
        example.or().andLike("id","2");

        List<User> users=this.userMapper.selectByExample(example);
         System.out.println(users);

    }


    @Test
    public void testMdpPeopleEntity(){

        MdpPeopleEntity mdpPeopleEntity=new MdpPeopleEntity();
        mdpPeopleEntity.setName("%玉%");
        mdpPeopleEntity.setPhoneNum("%18580%");
        List<MdpPeopleEntity> mdpPeopleEntities=this.mdpPeopleEntityService.queryMdpPeopleEntityByLike(mdpPeopleEntity);
        System.out.println(mdpPeopleEntities);

    }



}
