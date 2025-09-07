package com.example.blog.repository;

import com.example.blog.domain.Post;
import com.example.blog.domain.dto.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleIgnoreCase(String title); //

    /*
    Referências:
        https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/
        https://docs.spring.io/spring-data/data-document/docs/current/reference/html/
     */
}
