package com.example.store.repository;

import com.example.store.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository  extends JpaRepository<UserProfile, Long> {
}
