package com.giannis.blogApi.service;

import com.giannis.blogApi.entity.User;
import com.giannis.blogApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

            User user = userRepository.findByEmail(s);
            CustomUserDetails userDetails = null;
            if(user!=null){
                userDetails = new CustomUserDetails();
                userDetails.setUser(user);
            }else {
                throw new UsernameNotFoundException("User dont exist with name " + s);
            }
        return userDetails;
    }
}
