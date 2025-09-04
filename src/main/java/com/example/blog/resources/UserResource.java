package com.example.blog.resources;

import com.example.blog.domain.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
       try {
           var data = userService.findAll();
           return ResponseEntity.ok().body(data);
       }
       catch (Exception e) {
           return ResponseEntity.notFound().build();
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        try {
            var data = userService.findById(id);
            return ResponseEntity.ok().body(data);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
