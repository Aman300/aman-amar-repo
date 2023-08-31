package com.hello.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {

  @Autowired
  UserRepository userRepository;

   @GetMapping("/")
    public ApiResponse hello() {
        ApiResponse response = new ApiResponse();
        response.setStatus(true);
        response.setMessage("Hello, world!");
        return response;
    }

  @GetMapping("/get-user")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/get-single-user/{id}")
  public User getSigleUser(@PathVariable Integer id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      userRepository.findById(id);
    }
    return user;
  }

  @PostMapping("/user-post")

  public User savUser(@RequestBody User user) {
    return userRepository.save(user);
  }

  @GetMapping("/user-delete/{id}")
  public User deleteUser(@PathVariable Integer id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      userRepository.deleteById(id);
    }
    return user;
  }

  @PutMapping("/user-update/{id}")
  public User updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      user.setName(updatedUser.getName());
      user.setEmail(updatedUser.getEmail());
      // Update other properties as needed
      userRepository.save(user);
    }
    return user;
  }

}
  


