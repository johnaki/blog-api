package com.giannis.blogApi.service;

import com.giannis.blogApi.dto.PostDTO;
import com.giannis.blogApi.entity.Post;

import java.util.List;

public interface PostService {

    List<PostDTO> findAll();

    PostDTO findById(Long id);

    PostDTO save(PostDTO post) throws Exception;

    void deleteById(Long id);

    List<PostDTO> getTop10PostOfUser(Long userId);

    List<PostDTO> getAllByCategory(Long categoryId);

    List<PostDTO> getAllByBlog(Long blogId);

    PostDTO toDTO(Post post);

    Post toEntity(PostDTO postDTO);
}
