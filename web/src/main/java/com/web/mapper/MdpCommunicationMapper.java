package com.hikvision.mdp.web.mapper;

import com.github.abel533.mapper.Mapper;
import com.hikvision.mdp.web.pojo.MdpCommunicationsInfoReflect;
import com.hikvision.mdp.web.pojo.MdpCommunicationsInfo;

import java.util.List;

/**
 * Created by geting on 2017/1/19.
 */
public interface MdpCommunicationMapper extends Mapper<MdpCommunicationsInfo>{
  public List<MdpCommunicationsInfoReflect> queryMdpCommunicationsInfoReflect(MdpCommunicationsInfo mdpCommunicationsInfo);


}
