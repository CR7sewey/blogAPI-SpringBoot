package com.example.blog.services;

import com.example.blog.domain.Post;
import com.example.blog.domain.dto.UserDTO;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IService<Post> {
    @Autowired
    PostRepository postRepository;


    @Override
    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post not found"));
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post insert(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        var post1 = postRepository.findById(post.getId()).orElseThrow(() -> new ObjectNotFoundException("Post not found"));
        post1.setTitle(post.getTitle());
        post1.setBody(post.getBody());
        post1.setDate(post.getDate());

        return postRepository.save(post1);
    }

    @Override
    public void delete(String id) {
        postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post not found"));
        postRepository.deleteById(id);
    }
}
