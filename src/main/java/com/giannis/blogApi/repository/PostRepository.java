package com.giannis.blogApi.repository;

import com.giannis.blogApi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

//    @Query(value = "SELECT * FROM Post p WHERE p.user_id=:userId ORDER BY p.date DESC LIMIT 10")
    List<Post> findTop10ByUserIdOrderByDateDesc(Long userId);

    List<Post> findAllByOrderByDateDesc();

    List<Post> findAllByCategoriesIdEquals(Long categoryId);

    List<Post> findAllByUserIdOrderByDateDesc(Long userId);
}
