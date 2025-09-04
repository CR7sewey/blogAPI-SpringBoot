package com.example.blog.resources;

import com.example.blog.domain.User;
import com.example.blog.domain.dto.UserDTO;
import com.example.blog.repository.UserRepository;
import com.example.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
           )).collect(Collectors.toList());
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        User user = userService.userFromDTO(userDTO);
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value = "/{id}", method =RequestMethod.PUT )
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable String id) {
        User user = userService.userFromDTO(userDTO);
        user.setId(id);
        user = userService.update(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
