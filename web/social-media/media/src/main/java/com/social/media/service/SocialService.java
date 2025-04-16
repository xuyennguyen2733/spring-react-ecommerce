package com.social.media.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.media.model.SocialUser;
import com.social.media.repository.SocialUserRepository;

@Service
public class SocialService {
  @Autowired 
  private SocialUserRepository socialUserRepository;


  public List<SocialUser> getAllUsers() {
    return socialUserRepository.findAll();
  }

  public SocialUser saveUser(SocialUser socialUser) {
    return socialUserRepository.save(socialUser);
  }

  public SocialUser deleteUser(Long id) {
    SocialUser socialUser = socialUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    socialUserRepository.delete(socialUser);

    return socialUser;
  }
}
