package com.example.userList.controller;

import com.example.userList.service.UserListServiceImpl;
import com.example.userList.util.ResponseBean;
import com.example.userList.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserHandler {

    @Autowired
    UserListServiceImpl userList;

    @GetMapping("/")
    public ResponseEntity predefineUser(){
        ResponseBean response = new ResponseBean();
        userList.initUser();
        response.setMessage("PreDefined Users are initialized");
        response.setId(0);
        return new ResponseEntity<ResponseBean>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllUserById")
    public ResponseEntity showAllUserById() {
        return new ResponseEntity<List<User>>(userList.showAllUserSortedById(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAllUserByName")
    public ResponseEntity showAllUserByName() {
        return new ResponseEntity<List<User>>(userList.showAllUserSortedByName(), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<ResponseBean> handleUsers(@RequestBody User user) {
        ResponseBean response = new ResponseBean();
        if(userList.addUser(user)){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Id", String.valueOf(user.getId()));
            response.setMessage("User is Added Successfully");
            response.setId(user.getId());
            return new ResponseEntity<ResponseBean>(response, headers, HttpStatus.CREATED);
        }
        else {
            response.setMessage("User Already Exists.");
            response.setId(user.getId());
            return new ResponseEntity<ResponseBean>(response, HttpStatus.ALREADY_REPORTED);
        }
    }

    @PostMapping(value = "/updateUser/{Id}")
    public ResponseEntity<ResponseBean> handleUsersUpdate(@PathVariable("Id") Integer Id, @RequestBody User user) {
        ResponseBean response = new ResponseBean();
        if(userList.updateUser(Id, user)){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Id", String.valueOf(user.getId()));
            response.setMessage("User is Updated Successfully");
            response.setId(user.getId());
            return new ResponseEntity<ResponseBean>(response, headers, HttpStatus.ACCEPTED);
        }
        else {
            response.setMessage("Given Id is already Registered.");
            response.setId(user.getId());
            return new ResponseEntity<ResponseBean>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity showUser(@PathVariable("id") Integer Id) {
        return new ResponseEntity<User>(userList.showSingleUser(Id), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/deleteUser/{id}")
    public ResponseEntity deleteUsers(@PathVariable("id") Integer Id) {
        ResponseBean responseBean = new ResponseBean();
        if(userList.removeUser(Id)){
            responseBean.setId(Id);
            responseBean.setMessage("User deleted Successfully");
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.ACCEPTED);
        }
        else {
            responseBean.setId(500);
            responseBean.setMessage("Something went wrong");
            return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
