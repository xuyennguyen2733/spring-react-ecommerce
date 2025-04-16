package com.social.media.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.model.SocialUser;
import com.social.media.service.SocialService;

@RestController
public class SocialController {
  @Autowired
  private SocialService socialService;

  @GetMapping("/social/users")
  public ResponseEntity<List<SocialUser>> getUsers() {
    return new ResponseEntity<>(socialService.getAllUsers(), HttpStatus.OK);
  }

  @PostMapping("/social/users")
  public ResponseEntity<SocialUser> createUsers(@RequestBody SocialUser socialUser) {
    return new ResponseEntity<>(socialService.saveUser(socialUser), HttpStatus.OK);
  }

  @DeleteMapping("/social/users/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    socialService.deleteUser(id);
    return new ResponseEntity<>("deleted user successfully", HttpStatus.OK);
  }
}
