package com.example.blog.resources;

import com.example.blog.domain.User;
import com.example.blog.domain.dto.UserDTO;
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
    public ResponseEntity<List<UserDTO>> findAll() {
       try {
           var data = userService.findAll();
           var dataDTO = data.stream().map(user -> new UserDTO(
                   user.getId(),
                   user.getName(),
                   user.getEmail()
           )).toList();
           return ResponseEntity.ok().body(dataDTO);
       }
       catch (Exception e) {
           return ResponseEntity.notFound().build();
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        try {
            var data = userService.findById(id);
            UserDTO userDTO = new UserDTO(
                    data.getId(),
                    data.getName(),
                    data.getEmail()
            );
            return ResponseEntity.ok().body(userDTO);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
