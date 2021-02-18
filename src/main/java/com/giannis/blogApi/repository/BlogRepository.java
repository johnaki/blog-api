package com.giannis.blogApi.repository;

import com.giannis.blogApi.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Long> {

    Blog findByUserId(Long userId);

}
