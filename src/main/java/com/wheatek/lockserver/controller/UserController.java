package com.wheatek.lockserver.controller;

import com.wheatek.lockserver.bean.User;
import com.wheatek.lockserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/{phoneNumber}")
    public User getUser(@PathVariable("phoneNumber") String phoneNumber){
        return userService.getUserByPhoneNumber(phoneNumber);
    }

    @RequestMapping("/user")
    public int insertUser(User user){
        return userService.insertUser(user);
    }

    @ResponseBody
    @RequestMapping("/user/update")
    public int updateUser(User user){
        return userService.updateUser(user);
    }

    @ResponseBody
    @RequestMapping("/user/delete/{phoneNumber}")
    public int deleteUserByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        return userService.deleteUserByPhoneNumber(phoneNumber);
    }

}
