package com.example.blog.resources;

import com.example.blog.domain.Post;
import com.example.blog.domain.dto.UserDTO;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.services.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getAll() {
        List<Post> posts = postRepository.findAll();
        return ResponseEntity.ok().body(posts);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Post> getById(@PathVariable String id) {
        Optional<Post> post = postRepository.findById(id);
        return post.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/titlesearch")
    public ResponseEntity<List<Post>> getByTitle(@RequestParam String title) {
        var url = Utils.urlDecoder(title);
        List<Post> posts = postRepository.findByTitleIgnoreCase(url);
        return ResponseEntity.ok().body(posts);
    }

}
