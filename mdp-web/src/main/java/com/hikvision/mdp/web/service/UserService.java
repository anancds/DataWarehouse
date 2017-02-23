package com.hikvision.mdp.web.service;

import com.github.abel533.entity.Example;
import com.hikvision.mdp.web.mapper.UserMapper;
import com.hikvision.mdp.web.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by geting on 2017/1/18.
 */


@SuppressWarnings("all")
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> queryUser(){

        List<User> users=this.userMapper.select(null);
        return users;
    }
}
