package com.sl.mecm.service.user.dao;

import com.sl.mecm.core.commons.web.UserAccountInfo;
import com.sl.mecm.service.user.config.SqlConfigs;
import com.sl.mecm.service.user.constant.Columns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserAccountDao {

    @Autowired
    private SqlConfigs sqlConfigs;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserAccountInfo getAccountInfoById(String accountId){
        Assert.hasText(accountId, "account id can not be empty.");
        List<UserAccountInfo> infoList = jdbcTemplate.query(
                sqlConfigs.getQueryUserAccountInfoById(),
                ps -> ps.setString(1, accountId),
                new UserAccountInfoMapper());
        return infoList.get(0);
    }

    public UserAccountInfo getAccountInfoByUsername(String username){
        Assert.hasText(username, "user name can not be empty.");
        List<UserAccountInfo> infoList = jdbcTemplate.query(
                sqlConfigs.getQueryUserAccountInfoByName(),
                ps -> ps.setString(1, username),
                new UserAccountInfoMapper());
        return infoList.get(0);
    }

    public static class UserAccountInfoMapper implements RowMapper<UserAccountInfo>{
        @Override
        public UserAccountInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserAccountInfo userAccountInfo = new UserAccountInfo();
            userAccountInfo.setAccountId(rs.getString(Columns.ACCOUNT_ID));
            userAccountInfo.setUsername(rs.getString(Columns.USERNAME));
            userAccountInfo.setMerchantId(rs.getString(Columns.MERCHANT_ID));
            userAccountInfo.setMerchantRoles(rs.getString(Columns.MERCHANT_ROLES));
            userAccountInfo.setCreateTime(rs.getDate(Columns.CREATE_TIME));
            userAccountInfo.setUpdateTime(rs.getDate(Columns.UPDATE_TIME));
            return userAccountInfo;
        }
    }
}
