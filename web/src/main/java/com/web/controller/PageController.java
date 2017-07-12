package com.hikvision.mdp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by geting on 2017/1/18.
 */

@Controller
@RequestMapping
public class PageController {
    @RequestMapping(value="{pageName}",method= RequestMethod.GET)
    public String toPage(@PathVariable(value="pageName") String pageName){
        return pageName;
    }
}
