package com.project.inventory.dao;

import com.project.inventory.entity.UserDetails;
import java.util.List;
import java.util.NoSuchElementException;

public interface UserLoginDao {

    int saveUserDetails (UserDetails userDetails);
    int isExistsUser(String userName);
    int validateLogin(UserDetails userDetails);
    int getFailedAttempts(String userName) throws NoSuchElementException;
    void updateFailedAttempts(String userName);
    int updateUserDetails(UserDetails userDetails);
    int deleteUserDetails(String userName);
    List<UserDetails> getAllUserDetails();

}
