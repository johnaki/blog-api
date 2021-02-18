package com.giannis.blogApi.service;

import com.giannis.blogApi.dto.BlogDTO;
import com.giannis.blogApi.entity.Blog;
import com.giannis.blogApi.entity.Category;
import com.giannis.blogApi.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public List<BlogDTO> findAll() {
        List<Blog> blogs = blogRepository.findAll();
        List<BlogDTO> blogDTOS = new ArrayList<>();
        for(Blog blog : blogs){
            BlogDTO blogDTO = this.toDTO(blog);
            blogDTOS.add(blogDTO);
        }
        return blogDTOS;
    }

    @Override
    @Transactional
    public BlogDTO findById(Long id) {
        Blog blog ;
        try{
        blog = blogRepository.findById(id).get();
        }catch (RuntimeException e){
            throw new RuntimeException("Blog id not found - "+ id);
        }
        BlogDTO blogToReturn = this.toDTO(blog);
        return blogToReturn;
    }

    @Override
    @Transactional
    public BlogDTO findByUserId(Long userId) {
        Blog blog;
        try{
            blog = blogRepository.findByUserId(userId);
        }catch (RuntimeException e){
            throw new RuntimeException("Blog not found ");
        }
        BlogDTO blogToReturn = this.toDTO(blog);
        return blogToReturn;
    }

    @Override
    @Transactional
    public BlogDTO save(BlogDTO blogDTO) throws Exception {
//        blogDTO.setUserId((long)3);
        Blog theBlog = toEntity(blogDTO);
        try{
            BlogDTO  savedBlog = this.toDTO(blogRepository.save(theBlog));
            return savedBlog;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public BlogDTO toDTO(Blog blog){
        BlogDTO blogDTO = new BlogDTO();

        blogDTO.setId(blog.getId());
        blogDTO.setDescription(blog.getDescription());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setUserId(blog.getUser().getId());
        List<Long> categoryIds = new ArrayList<>();
        for(Category category:blog.getCategories()){
            categoryIds.add(category.getId());
        }
        blogDTO.setCategoryIds(categoryIds);
        return blogDTO;
    }

    @Override
    public Blog toEntity(BlogDTO blogDTO) {
        Blog blog = new Blog();

        blog.setId(blogDTO.getId());
        blog.setDescription(blogDTO.getDescription());
        blog.setTitle(blogDTO.getTitle());
        blog.setUser(userService.toEntity(userService.findById(blogDTO.getUserId())));

        return blog;
    }
}
