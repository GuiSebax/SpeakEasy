package com.core.SpeakEasy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.core.SpeakEasy.service.UserService;
import com.core.SpeakEasy.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    Optional<User> user = userService.getUserById(id);
    return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
  
  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.saveUser(user);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
    Optional<User> existingUser = userService.getUserById(id);
    return existingUser.map(user -> {
      user.setName(updatedUser.getName());
      user.setEmail(updatedUser.getEmail());
      user.setPassword(updatedUser.getPassword());
      user.setLevel(updatedUser.getLevel());
      user.setXp(updatedUser.getXp());
      return ResponseEntity.ok(userService.saveUser(updatedUser));
    })
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    if (userService.getUserById(id).isPresent()) {
      userService.deleteUser(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

}
