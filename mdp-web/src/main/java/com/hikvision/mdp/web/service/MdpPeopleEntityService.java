package com.hikvision.mdp.web.service;

import com.github.abel533.entity.Example;
import com.hikvision.mdp.web.mapper.MdpPeopleEntityMapper;
import com.hikvision.mdp.web.pojo.MdpPeopleEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.List;

/**
 * Created by geting on 2017/2/23.
 */
@Service
@SuppressWarnings("all")
public class MdpPeopleEntityService {
    @Autowired
    private MdpPeopleEntityMapper mdpPeopleEntityMapper;

    public List<MdpPeopleEntity> queryMdpPeopleEntityByLike(MdpPeopleEntity mdpPeopleEntity) throws  Exception{

        Example example=new Example(MdpPeopleEntity.class);

        List<MdpPeopleEntity> mdpPeopleEntities=null;
        if(StringUtils.isEmpty(mdpPeopleEntity.getName())&&StringUtils.isEmpty(mdpPeopleEntity.getCardId())&&StringUtils.isEmpty(mdpPeopleEntity.getPhoneNum())&&StringUtils.isEmpty(mdpPeopleEntity.getSex())){
            return mdpPeopleEntities;
        }

        if(StringUtils.isNotEmpty(mdpPeopleEntity.getName())){
            example.createCriteria().andLike("name","%"+ URLDecoder.decode(mdpPeopleEntity.getName(),"utf-8")+"%");
        }
        if(StringUtils.isNotEmpty(mdpPeopleEntity.getCardId())){
            example.createCriteria().andLike("cardId","%"+mdpPeopleEntity.getCardId()+"%");
        }
        if(StringUtils.isNotEmpty(mdpPeopleEntity.getPhoneNum())){
            example.createCriteria().andLike("phoneNum","%"+mdpPeopleEntity.getPhoneNum()+"%");
        }

        if(StringUtils.isNotEmpty(mdpPeopleEntity.getSex())){
            example.createCriteria().andLike("sex","%"+mdpPeopleEntity.getSex()+"%");
        }

       mdpPeopleEntities= this.mdpPeopleEntityMapper.selectByExample(example);

        return mdpPeopleEntities;
    }
}
