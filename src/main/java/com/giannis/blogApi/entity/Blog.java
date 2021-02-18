package com.giannis.blogApi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="title")
    private String title;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name="description")
    private String description;
    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL)
    private List<Category> categories;

    public Blog() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
