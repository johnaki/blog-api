package com.giannis.blogApi.repository;

import com.giannis.blogApi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    //    @Query(value = "SELECT * FROM Category c WHERE c.blog_id=:blogId")
    List<Category> findAllByBlogId(Long blogId);

}
