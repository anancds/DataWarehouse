package com.hikvision.mdp.web.controller;

import com.hikvision.mdp.web.bean.MdpTimeSpaceDynamicOnontology;
import com.hikvision.mdp.web.pojo.MdpCommunicationsInfo;
import com.hikvision.mdp.web.pojo.MdpOntologyObjectRelationship;
import com.hikvision.mdp.web.service.MdpTimeSpaceRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by geting on 2017/2/8.
 */

@Controller
@RequestMapping("relationship")
public class MdpTimeSpaceRelationController {

    @Autowired
    private MdpTimeSpaceRelationService mdpTimeSpaceRelationService;

    @RequestMapping(value = "full-dose", method = RequestMethod.GET)
    public ResponseEntity<MdpTimeSpaceDynamicOnontology> getAllMdpTimeSpaceDynamicOnontology(){
        try {
            MdpTimeSpaceDynamicOnontology mdpTimeSpaceDynamicOnontology =this.mdpTimeSpaceRelationService.queryfullDoseDynamicOnontology();
            if (mdpTimeSpaceDynamicOnontology != null) {
                return ResponseEntity.ok(mdpTimeSpaceDynamicOnontology);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


    @RequestMapping(value = "ids-hierarchy", method = RequestMethod.GET)
    public ResponseEntity<MdpTimeSpaceDynamicOnontology> getAllMdpTimeSpaceDynamicOnontologyByIdsAndAndHierarchy(@RequestParam(value = "ids") String ids, @RequestParam(value = "hierarchy")Integer hierarchy){
        try {
            MdpTimeSpaceDynamicOnontology mdpTimeSpaceDynamicOnontology =this.mdpTimeSpaceRelationService.queryDynamicOnontologyByIdsAndHierarchy(ids,hierarchy);
            if (mdpTimeSpaceDynamicOnontology != null) {
                return ResponseEntity.ok(mdpTimeSpaceDynamicOnontology);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }


}
