package com.example.blog.domain;

import com.example.blog.domain.dto.CommentDTO;
import com.example.blog.domain.dto.UserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document(collection = "posts")
public class Post implements Serializable {

    @Id
    private String id;
    private String title;
    private String body;
    private Date date;

    private List<CommentDTO> comments = new ArrayList<>();

    private UserDTO author;

    public Post() {}

    public Post(String id, String title, String body, Date date, UserDTO author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
        this.author = author;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public UserDTO getAuthor() {
        return author;
    }
    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "postId: " + this.id + " " + this.title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(body, post.body);
    }


}
