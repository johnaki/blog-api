package com.giannis.blogApi.dto;

public class CategoryDTO {

    private Long id;
    private Long blogId;
    private String description;

    public CategoryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
