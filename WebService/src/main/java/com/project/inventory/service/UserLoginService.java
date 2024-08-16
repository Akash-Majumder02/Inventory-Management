package com.project.inventory.service;

import com.project.inventory.dao.UserLoginDao;
import com.project.inventory.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLoginService {

    @Autowired
    private UserLoginDao userLoginDao;

    public String saveUserDetails(UserDetails userDetails){
        int isExist = userLoginDao.isExistsUser(userDetails.getUserName());
        if(isExist==0){
            int retVal = userLoginDao.saveUserDetails(userDetails);
            if(retVal==1)
                return "User saved successfully";
            else
                return "Error while saving user";
        }else
            return "User already exists";
    }

    public String validateLogin(UserDetails userDetails) {
        boolean userExists = userLoginDao.isExistsUser(userDetails.getUserName()) > 0;
        if (!userExists) {
            return "User doesn't exists!!";
        }
        int failedAttempts = userLoginDao.getFailedAttempts(userDetails.getUserName());
        if (failedAttempts >= 5) {
            return "Account locked!!, contact administrator";
        }
        int loginResult = userLoginDao.validateLogin(userDetails);
        if (loginResult == 1) {
            return "Login successful!!";
        } else {
            userLoginDao.updateFailedAttempts(userDetails.getUserName());
            return "Login failed!!";
        }
    }

    public String updateUserDetails(UserDetails userDetails) {
        int retVal = userLoginDao.updateUserDetails(userDetails);
        if(retVal==1)
            return "User updated successfully";
        else
            return "Error while updating user";
    }

    public String deleteUserDetails(String userName) {
        int retVal = userLoginDao.deleteUserDetails(userName);
        if(retVal==1)
            return "User deleted successfully";
        else
            return "Error while deleting user";
    }

    public List<UserDetails> getAllUserDetails() {
        return userLoginDao.getAllUserDetails();
    }

}
