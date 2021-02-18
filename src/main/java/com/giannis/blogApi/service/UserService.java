package com.giannis.blogApi.service;

import com.giannis.blogApi.dto.UserDTO;
import com.giannis.blogApi.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO findByEmail(String email);

    void deleteById(Long id);

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

}
