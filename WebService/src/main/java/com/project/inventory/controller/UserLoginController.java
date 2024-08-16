package com.project.inventory.controller;

import com.project.inventory.entity.DataWrapper;
import com.project.inventory.entity.UserDetails;
import com.project.inventory.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping(value = "/saveUserDetails" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> saveUserDetails(@RequestBody UserDetails userDetails){
        DataWrapper dataWrapper = new DataWrapper();
        try{
            String message = userLoginService.saveUserDetails(userDetails);
            if (message.contains("success")) {
                dataWrapper.setMessage(message);
                dataWrapper.setStatus("SUCCESS");
            } else {
                dataWrapper.setMessage(message);
                dataWrapper.setStatus("ERROR");
            }
            return new ResponseEntity<>(dataWrapper, HttpStatus.OK);
        }catch (Exception e){
            dataWrapper.setMessage("ERROR:" + e.getMessage());
            dataWrapper.setStatus("ERROR");
            return new ResponseEntity<>(dataWrapper, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAllUserDetails" )
    @ResponseBody
    public ResponseEntity<?> getAllUserDetails(){
        DataWrapper dataWrapper = new DataWrapper();
        try{
            List<UserDetails> userDetailsList = userLoginService.getAllUserDetails();
            if (!userDetailsList.isEmpty()) {
                dataWrapper.setDataList(userDetailsList);
                dataWrapper.setMessage("Users fetched successfully!!");
                dataWrapper.setStatus("SUCCESS");
            } else {
                dataWrapper.setDataList(null);
                dataWrapper.setMessage("No users found!!");
                dataWrapper.setStatus("ERROR");
            }
            return new ResponseEntity<>(dataWrapper, HttpStatus.OK);
        }catch (Exception e){
            dataWrapper.setDataList(null);
            dataWrapper.setMessage("Error : " + e.getMessage());
            dataWrapper.setStatus("ERROR");
            return new ResponseEntity<>(dataWrapper, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/validateLogin" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> validateLogin(@RequestBody UserDetails userDetails) {
        DataWrapper dataWrapper = new DataWrapper();
        try {
            String message = userLoginService.validateLogin(userDetails);
            if (message.contains("success")) {
                dataWrapper.setMessage(message);
                dataWrapper.setStatus("SUCCESS");
            } else {
                dataWrapper.setMessage(message);
                dataWrapper.setStatus("ERROR");
            }
            return new ResponseEntity<>(dataWrapper, HttpStatus.OK);
        } catch (Exception e) {
            dataWrapper.setMessage("ERROR:" + e.getMessage());
            dataWrapper.setStatus("ERROR");
            return new ResponseEntity<>(dataWrapper, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/updateUserDetails" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> updateUserDetails(@RequestBody UserDetails userDetails){
        DataWrapper dataWrapper = new DataWrapper();
        try{
            String message = userLoginService.updateUserDetails(userDetails);
            if (message.contains("success")) {
                dataWrapper.setMessage(message);
                dataWrapper.setStatus("SUCCESS");
            } else {
                dataWrapper.setMessage(message);
                dataWrapper.setStatus("ERROR");
            }
            return new ResponseEntity<>(dataWrapper, HttpStatus.OK);
        }catch (Exception e){
            dataWrapper.setMessage("ERROR:" + e.getMessage());
            dataWrapper.setStatus("ERROR");
            return new ResponseEntity<>(dataWrapper, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/deleteUserDetails" , consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> deleteUserDetails(@RequestBody UserDetails userDetails){
        DataWrapper dataWrapper = new DataWrapper();
        try{
            String message = userLoginService.deleteUserDetails(userDetails.getUserName());
            if (message.contains("success")) {
                dataWrapper.setMessage(message);
                dataWrapper.setStatus("SUCCESS");
            } else {
                dataWrapper.setMessage(message);
                dataWrapper.setStatus("ERROR");
            }
            return new ResponseEntity<>(dataWrapper, HttpStatus.OK);
        }catch (Exception e){
            dataWrapper.setMessage("ERROR:" + e.getMessage());
            dataWrapper.setStatus("ERROR");
            return new ResponseEntity<>(dataWrapper, HttpStatus.BAD_REQUEST);
        }
    }

}
