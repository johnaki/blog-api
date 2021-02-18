package com.giannis.blogApi.service;

import com.giannis.blogApi.dto.CategoryDTO;
import com.giannis.blogApi.entity.Blog;
import com.giannis.blogApi.entity.Category;
import com.giannis.blogApi.repository.BlogRepository;
import com.giannis.blogApi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BlogRepository blogRepository;

    @Override
    @Transactional
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category category : categories){
            CategoryDTO categoryDTO = this.toDTO(category);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    @Override
    @Transactional
    public CategoryDTO findById(Long id) {

        Category category;
        try{
            category = categoryRepository.findById(id).get();
        }catch (RuntimeException e){
            throw new RuntimeException("Category id not found - "+ id);
        }
        CategoryDTO categoryToReturn = this.toDTO(category);
        return categoryToReturn;
    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category theCategory = toEntity(categoryDTO);
        return this.toDTO(categoryRepository.save(theCategory));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<CategoryDTO> findAllByBlog(Long blogId) {
        List<Category> categories;
        try{
            categories = categoryRepository.findAllByBlogId(blogId);
        }catch (RuntimeException e){
            throw new RuntimeException("Blog id not found - "+ blogId);
        }
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for(Category category:categories){
            CategoryDTO categoryDTO = toDTO(category);
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    @Override
    public CategoryDTO toDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setBlogId(category.getBlog().getId());

        return categoryDTO;
    }

    @Override
    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        Blog blog;
        if (categoryDTO.getId() != null){
            category.setId(categoryDTO.getId());
        }
        if (blogRepository.findById(categoryDTO.getBlogId()).isPresent()) {
            blog = blogRepository.findById(categoryDTO.getBlogId()).get();
            category.setBlog(blog);
        }
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
}
