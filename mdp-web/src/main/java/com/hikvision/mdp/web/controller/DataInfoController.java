package com.hikvision.mdp.web.controller;

import com.hikvision.mdp.web.pojo.User;
import com.hikvision.mdp.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by geting on 2017/1/18.
 */

@Controller
@RequestMapping("api-interfeace")
public class DataInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="connection/{flag}",method= RequestMethod.GET)
    public ResponseEntity<List<User>> getPhoneConnectionData(String flag) {

        List<User> users=this.userService.queryUser();
        return ResponseEntity.ok(users);

    }
}
