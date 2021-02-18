package com.giannis.blogApi.service;

import com.giannis.blogApi.dto.CategoryDTO;
import com.giannis.blogApi.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();

    CategoryDTO findById(Long id);

    CategoryDTO save(CategoryDTO post);

    void deleteById(Long id);

    List<CategoryDTO> findAllByBlog(Long blogId);

    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO postDTO);
}
