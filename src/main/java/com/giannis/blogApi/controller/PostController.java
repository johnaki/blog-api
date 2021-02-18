package com.giannis.blogApi.controller;

import com.giannis.blogApi.dto.PostDTO;
import com.giannis.blogApi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/get")
    public List<PostDTO> findAll(){
        return postService.findAll();
    }

    @GetMapping("/{postId}")
    public PostDTO getPost(@PathVariable Long postId){
        return postService.findById(postId);
    }

    @PutMapping("/save")
    public void createEditPost(@RequestBody PostDTO post) throws Exception {
        postService.save(post);
    }


    @DeleteMapping("/delete")
    public void deletePost(@RequestParam Long id){

        PostDTO tempPost = postService.findById(id);

        if(tempPost==null){
            throw new RuntimeException("Post id not found - "+ id);
        }

        postService.deleteById(id);

    }

    @GetMapping("/getforuser/{userId}")
    public List<PostDTO> getTop10ForUser(@PathVariable Long userId){
        return postService.getTop10PostOfUser(userId);
    }

    @GetMapping("/getforcategory/{categoryId}")
    public List<PostDTO> getAllForCategory(@PathVariable Long categoryId){
        return postService.getAllByCategory(categoryId);
    }

    @GetMapping("/getforblog/{blogId}")
    public List<PostDTO> getAllForBlog(@PathVariable Long blogId){
        return postService.getAllByBlog(blogId);
    }
}
