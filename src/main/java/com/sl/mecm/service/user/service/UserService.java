package com.sl.mecm.service.user.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.sl.mecm.service.user.dao.UserAccountDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserAccountDao userAccountDao;

    public Mono<JSONObject> getUserAccountInfoByName(String username){
        return Mono.just(username)
                .map(theUsername -> userAccountDao.getAccountInfoByUsername(theUsername))
                .map(userAccountInfo -> JSON.parseObject(JSON.toJSONString(userAccountInfo)));
    }
}
