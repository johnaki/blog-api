package com.giannis.blogApi.service;

import com.giannis.blogApi.dto.BlogDTO;
import com.giannis.blogApi.entity.Blog;

import java.util.List;

public interface BlogService {

    List<BlogDTO> findAll();

    BlogDTO findById(Long id);

    BlogDTO findByUserId(Long userId);

    BlogDTO save(BlogDTO post) throws Exception;

    void deleteById(Long id);

    BlogDTO toDTO(Blog blog);

    Blog toEntity(BlogDTO blogDTO);
}
