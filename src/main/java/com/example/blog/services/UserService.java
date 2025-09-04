package com.example.blog.services;

import com.example.blog.domain.User;
import com.example.blog.domain.dto.UserDTO;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User>
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        var user1 = userRepository.findById(user.getId()).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        return userRepository.save(user1);
    }

    @Override
    public void delete(String id) {
        userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        userRepository.deleteById(id);
    }

    public User userFromDTO(UserDTO userDTO) {
        var u = userRepository.findById(userDTO.getId()).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        return u;
    }
}
