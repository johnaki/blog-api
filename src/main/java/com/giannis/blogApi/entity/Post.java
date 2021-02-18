package com.giannis.blogApi.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "categories_has_posts",
            joinColumns = @JoinColumn(name = "posts_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id", referencedColumnName="id")
    )
    private Set<Category> categories;

    @Column(name="date")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
