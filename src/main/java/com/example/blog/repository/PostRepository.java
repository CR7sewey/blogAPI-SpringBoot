package com.example.blog.repository;

import com.example.blog.domain.Post;
import com.example.blog.domain.dto.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
}
