package com.hikvision.mdp.web.controller;

import com.hikvision.mdp.web.bean.MdpCommunicationsInfoBean;
import com.hikvision.mdp.web.pojo.MdpPeopleEntity;
import com.hikvision.mdp.web.service.MdpPeopleEntityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by geting on 2017/2/23.
 */
@Controller
@RequestMapping("people")
public class MdpPeopleEntityController {
    @Autowired
    private MdpPeopleEntityService mdpPeopleEntityService;

    @RequestMapping("identity-info")
    public ResponseEntity<List<MdpPeopleEntity>> queryMdpPeopleEntityByLike(MdpPeopleEntity mdpPeopleEntity){
        try {
            List<MdpPeopleEntity> mdpPeopleEntities = this.mdpPeopleEntityService.queryMdpPeopleEntityByLike(mdpPeopleEntity);
            if (mdpPeopleEntities != null&&mdpPeopleEntities.size()>0) {
                return ResponseEntity.ok(mdpPeopleEntities);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

    }
}
