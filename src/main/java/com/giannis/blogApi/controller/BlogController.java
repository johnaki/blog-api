package com.giannis.blogApi.controller;

import com.giannis.blogApi.dto.BlogDTO;
import com.giannis.blogApi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/get")
    public List<BlogDTO> findAll(){

        return blogService.findAll();
    }

    @GetMapping("/{blogId}")
    public BlogDTO getBlog(@PathVariable Long blogId){

        BlogDTO theBlog = blogService.findById(blogId);

        if (theBlog==null){
            throw new RuntimeException("Blog id not found - "+ blogId);
        }
        return theBlog;
    }

    @PutMapping("/save")
    public BlogDTO createEditBlog(@RequestBody BlogDTO blog) throws Exception {
        return blogService.save(blog);
    }


    @DeleteMapping("/delete")
    public void deleteBlog(@RequestParam Long id){

        BlogDTO tempBlog = blogService.findById(id);

        if(tempBlog==null){
            throw new RuntimeException("Blog id not found - "+ id);
        }

        blogService.deleteById(id);

    }
}
