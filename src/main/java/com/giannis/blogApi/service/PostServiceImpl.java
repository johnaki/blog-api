package com.giannis.blogApi.service;

import com.giannis.blogApi.dto.CategoryDTO;
import com.giannis.blogApi.dto.PostDTO;
import com.giannis.blogApi.entity.Category;
import com.giannis.blogApi.entity.Post;
import com.giannis.blogApi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    @Override
    @Transactional
    public List<PostDTO> findAll() {
        List<Post> posts = postRepository.findAllByOrderByDateDesc();
        List<PostDTO> postDTOS = new ArrayList<>();
        for(Post post : posts){
            PostDTO postDTO = this.toDTO(post);
            postDTOS.add(postDTO);
        }
        return postDTOS;
    }

    @Override
    @Transactional
    public PostDTO findById(Long id) {
        Post post;
        try {
            post = postRepository.findById(id).get();
        }catch (RuntimeException e){
            throw new RuntimeException("Post id not found - "+ id);
        }
        PostDTO postToReturn = this.toDTO(post);
        return postToReturn;
    }

    @Override
    @Transactional
    public PostDTO save(PostDTO postDTO) throws Exception {
        Post thePost = toEntity(postDTO);
        Date currentDate = Calendar.getInstance().getTime();
        thePost.setDate(currentDate);
        thePost.setUser(userService.toEntity(userService.findById(postDTO.getUserId())));
        try{
            PostDTO savedPost = this.toDTO(postRepository.save(thePost));
            return savedPost;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<PostDTO> getTop10PostOfUser(Long userId) {
        List<Post> posts;
        try {
            posts = postRepository.findTop10ByUserIdOrderByDateDesc(userId);
        }catch (RuntimeException e){
            throw new RuntimeException("Post id not found - "+ userId);
        }
        List<PostDTO> postDTOS =new ArrayList<>();
        for (Post post:posts){
            PostDTO postDTO = toDTO(post);
            postDTOS.add(postDTO);
        }
        return postDTOS;
    }

    @Override
    @Transactional
    public List<PostDTO> getAllByCategory(Long categoryId) {
        List<Post> posts;
        try {
            posts = postRepository.findAllByCategoriesIdEquals(categoryId);
        }catch (RuntimeException e){
            throw new RuntimeException("Post id not found - "+ categoryId);
        }
        List<PostDTO> postDTOS =new ArrayList<>();
        for (Post post:posts){
            PostDTO postDTO = toDTO(post);
            postDTOS.add(postDTO);
        }
        return postDTOS;
    }

    @Override
    @Transactional
    public List<PostDTO> getAllByBlog(Long blogId) {
        List<Post> posts;
        Long userId = blogService.findById(blogId).getUserId();
        try {
            posts = postRepository.findAllByUserIdOrderByDateDesc(userId);
        }catch (RuntimeException e){
            throw new RuntimeException("Posts not found");
        }
        List<PostDTO> postDTOS =new ArrayList<>();
        for (Post post:posts){
            PostDTO postDTO = toDTO(post);
            postDTOS.add(postDTO);
        }
        return postDTOS;
    }

    @Override
    public PostDTO toDTO(Post post){
        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setDescription(post.getDescription());
        postDTO.setTitle(post.getTitle());
        List<Long> categoryIds =  new ArrayList<>();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        if(post.getCategories()!=null) {
            for (Category category : post.getCategories()) {
                categoryDTOList.add(categoryService.toDTO(category));
                categoryIds.add(category.getId());
            }
            postDTO.setCategories(categoryDTOList);
            postDTO.setCategoryIds(categoryIds);
        }
        postDTO.setDate(post.getDate());
        postDTO.setUserId(post.getUser().getId());

        return postDTO;
    }

    @Override
    public Post toEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        Set<Long> categoryIds =  new HashSet<>();
        Set<Category> categoryList = new HashSet<>();
        if(postDTO.getCategoryIds()!=null) {
            for (Long id : postDTO.getCategoryIds()) {
                CategoryDTO categoryDTO = categoryService.findById(id);
                categoryIds.add(categoryDTO.getId());
                categoryList.add(categoryService.toEntity(categoryDTO));
            }
            post.setCategories(categoryList);
        }
        post.setDate(postDTO.getDate());
        return post;
    }
}
