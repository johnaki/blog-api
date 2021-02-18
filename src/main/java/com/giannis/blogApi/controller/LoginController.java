package com.giannis.blogApi.controller;

import com.giannis.blogApi.dto.LoginDTO;
import com.giannis.blogApi.dto.UserDTO;
import com.giannis.blogApi.service.BlogService;
import com.giannis.blogApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/log")
    public LoginDTO login(@RequestParam String email, @RequestParam String password) throws Exception{
        LoginDTO loginDTO = new LoginDTO();
        UserDTO userDTO = null;
        userDTO = userService.findByEmail(email);
        if (userDTO!=null){
            if(userDTO.getPassword().equals(password)){
                    loginDTO.setMessage("success");
            }else{
                loginDTO.setMessage("Invalid Password");
            }
        }else{
            loginDTO.setMessage("Invalid Email");
        }
        loginDTO.setBlog(blogService.findByUserId(userDTO.getId()));
        loginDTO.setUserType(userDTO.getUserType());
        loginDTO.setName(userDTO.getName());
        loginDTO.setUserId(userDTO.getId());
        return loginDTO;
    }
}
