package com.sl.mecm.service.user.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.sl.mecm.service.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${mecm.user.base-path}")
@Slf4j
public class UserInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/info/by-name",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<JSONObject>> getUserAccountInfoByUsername(@RequestBody String content){
        JSONObject contentJson = JSON.parseObject(content);
        String username = contentJson.getString("username");
        return userService.getUserAccountInfoByName(username)
                .map(jsonObject -> new ResponseEntity<>(
                        new JSONObject()
                                .fluentPut("code", "200")
                                .fluentPut("message", "success")
                                .fluentPut("data", jsonObject),
                        HttpStatus.OK))
                .onErrorResume((Function<Throwable, Mono<ResponseEntity<JSONObject>>>) throwable -> {
                    log.error("error on retrieve account info:" + throwable.getMessage());
                    throwable.printStackTrace();
                    return Mono.just(new ResponseEntity<>(
                            JSONObject.of("code", "503", "message", "error to get account info"),
                            HttpStatus.SERVICE_UNAVAILABLE));
                });
    }
}
