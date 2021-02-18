package com.giannis.blogApi.controller;

import com.giannis.blogApi.dto.UserDTO;
import com.giannis.blogApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable Long userId){

        UserDTO theUser = userService.findById(userId);

        if (theUser==null){
            throw new RuntimeException("User id not found - "+ userId);
        }
        return theUser;
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long id){

        UserDTO tempUser = userService.findById(id);

        if(tempUser==null){
            throw new RuntimeException("User id not found - "+ id);
        }

        userService.deleteById(id);

    }
}
