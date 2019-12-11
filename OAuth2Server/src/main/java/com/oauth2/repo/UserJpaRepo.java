package com.oauth2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oauth2.model.User;

@Repository
public interface UserJpaRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}