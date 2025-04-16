package com.social.media.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.media.model.SocialProfile;

public interface SocialProfileRepository extends JpaRepository<SocialProfile, Long> {

}
