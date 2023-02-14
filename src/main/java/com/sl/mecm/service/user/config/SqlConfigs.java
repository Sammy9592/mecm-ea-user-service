package com.sl.mecm.service.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:configs/sqls.properties"})
public class SqlConfigs {

    @Value("${query-UserAccountInfo-by-name}")
    private String queryUserAccountInfoByName;

    @Value("${query-UserAccountInfo-by-id}")
    private String queryUserAccountInfoById;

    public String getQueryUserAccountInfoByName() {
        return queryUserAccountInfoByName;
    }

    public String getQueryUserAccountInfoById() {
        return queryUserAccountInfoById;
    }
}
