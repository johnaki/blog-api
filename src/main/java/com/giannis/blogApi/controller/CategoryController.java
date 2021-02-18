package com.giannis.blogApi.controller;

import com.giannis.blogApi.dto.CategoryDTO;
import com.giannis.blogApi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    public List<CategoryDTO> findAll(){

        return categoryService.findAll();
    }

    @GetMapping("/{categoryId}")
    public CategoryDTO getCategory(@PathVariable Long categoryId){

        CategoryDTO theCategory = categoryService.findById(categoryId);

        if (theCategory==null){
            throw new RuntimeException("Category id not found - "+ categoryId);
        }
        return theCategory;
    }

    @PutMapping("/save")
    public void createEditCategory(@RequestBody CategoryDTO category){
        categoryService.save(category);
    }


    @DeleteMapping("/delete")
    public void deleteCategory(@RequestParam Long id){

        CategoryDTO tempCategory = categoryService.findById(id);

        if(tempCategory==null){
            throw new RuntimeException("Category id not found - "+ id);
        }

        categoryService.deleteById(id);

    }

    @GetMapping("/getByBlog/{blogId}")
    public List<CategoryDTO> getAllByBlog(@PathVariable Long blogId){
        return categoryService.findAllByBlog(blogId);
    }
}
