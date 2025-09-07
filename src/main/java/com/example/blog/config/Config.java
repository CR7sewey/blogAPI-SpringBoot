package com.example.blog.config;

import com.example.blog.domain.Post;
import com.example.blog.domain.User;
import com.example.blog.domain.dto.CommentDTO;
import com.example.blog.domain.dto.UserDTO;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Configuration
@Profile("test")  // test bcs no application.properties esta
public class Config implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User user1 = new User(null, "John", "Doe", "123" );
        User user2 = new User(null, "Jane", "Doe", "123" );
        User user3 = new User(null, "Marie", "Smith", "123" );

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        List<User> users = userRepository.findAll();

        Date date = new Date();
        //var d1 = sdf.parse(date.toString());

        Post post1 = new Post(null, "Hello World", "Hello World", new Date(), new UserDTO(
                users.get(0).getId(),
                users.get(0).getName(),
                users.get(0).getEmail()
        ));
        Post post2 = new Post(null, "Hello World 2", "Hello World 2", new Date(), new UserDTO(
                users.get(0).getId(),
                users.get(0).getName(),
                users.get(0).getEmail()
        ));
        Post post3 = new Post(null, "Hello World 3", "Hello World 3", new Date(), new UserDTO(
                users.get(0).getId(),
                users.get(0).getName(),
                users.get(0).getEmail()
        ));


        CommentDTO c1 = new CommentDTO("Comment 1", new Date(), new UserDTO(
                users.get(0).getId(),
                users.get(0).getName(),
                users.get(0).getEmail()
        ));

        CommentDTO c2 = new CommentDTO("Comment 2", new Date(), new UserDTO(
                users.get(1).getId(),
                users.get(1).getName(),
                users.get(1).getEmail()
        ));

        post1.getComments().add(c1);
        post2.getComments().add(c2);


        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        user1.getPosts().addAll(Arrays.asList(post1, post2, post3));
        //user1.getComments().add(c1);
        //user2.getComments().add(c2);

        userRepository.save(user1);
        userRepository.save(user2);







    }
}
