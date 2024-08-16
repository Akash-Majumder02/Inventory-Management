package com.project.inventory.daoImpl;

import com.project.inventory.dao.UserLoginDao;
import com.project.inventory.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

import static com.project.inventory.InitialDataLoad.metaDataQueries;

@Repository
public class UserLoginDaoImpl implements UserLoginDao {

    @Autowired
    private JdbcClient jdbcClient;

    @Override
    public int saveUserDetails(UserDetails userDetails) {
        return jdbcClient.sql(metaDataQueries.get("saveUserDetails"))
                .paramSource(userDetails)
                .update();
    }

    @Override
    public int isExistsUser(String userName){
        return jdbcClient.sql(metaDataQueries.get("isExistsUser"))
                .param(1,userName)
                .query()
                .singleColumn()
                .size();
    }

    @Override
    public int validateLogin(UserDetails userDetails) {
        return jdbcClient.sql(metaDataQueries.get("validateLogin"))
                .param(1,userDetails.getUserName())
                .param(2,userDetails.getPassword())
                .query()
                .singleColumn()
                .size();
    }

    @Override
    public int getFailedAttempts(String userName) throws NoSuchElementException {
        return (int) jdbcClient.sql(metaDataQueries.get("getFailedAttempts"))
                .param(1,userName)
                .query()
                .singleValue();
    }

    @Override
    public void updateFailedAttempts(String userName) {
        jdbcClient.sql(metaDataQueries.get("updateFailedAttempts"))
                .param(1,userName)
                .update();
    }

    @Override
    public int updateUserDetails(UserDetails userDetails) {
        deleteUserDetails(userDetails.getUserName());
        return saveUserDetails(userDetails);
    }

    @Override
    public int deleteUserDetails(String userName) {
        return jdbcClient.sql(metaDataQueries.get("deleteUserDetails"))
                .param(1,userName)
                .update();
    }

    @Override
    public List<UserDetails> getAllUserDetails() {
        return jdbcClient.sql(metaDataQueries.get("getAllUserDetails"))
                .query(UserDetails.class)
                .list();
    }

}
