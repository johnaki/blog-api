package com.giannis.blogApi.repository;

import com.giannis.blogApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
