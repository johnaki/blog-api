package com.giannis.blogApi.dto;

public class LoginDTO {

    private String message;
    private BlogDTO blog;
    private String userType ;
    private String name;
    private Long userId;

    public LoginDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BlogDTO getBlog() {
        return blog;
    }

    public void setBlog(BlogDTO blog) {
        this.blog = blog;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
