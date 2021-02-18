package com.giannis.blogApi.service;

import com.giannis.blogApi.dto.UserDTO;
import com.giannis.blogApi.entity.User;
import com.giannis.blogApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired

    private UserRepository userRepository;

    @Override
    @Transactional
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User user : users){
            UserDTO userDTO = this.toDTO(user);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    @Transactional
    public UserDTO findById(Long id) {
        User user;
        try {
            user = userRepository.findById(id).get();
        }catch (RuntimeException e){
            throw new RuntimeException("User id not found - "+ id);
        }
        UserDTO userToReturn = this.toDTO(user);
        return userToReturn;
    }

    @Override
    @Transactional
    public UserDTO findByEmail(String email) {
        User user ;
        try {
            user = userRepository.findByEmail(email);
        }catch (RuntimeException e){
            throw new RuntimeException("User email not found - "+ email);
        }
        return toDTO(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserType(user.getUserType());

        return userDTO;
    }
    @Override
    public User toEntity(UserDTO userDTO){
        User user = new User();

        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setUserType(userDTO.getUserType());

        return user;
    }
}
