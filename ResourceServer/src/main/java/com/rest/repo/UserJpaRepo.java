package com.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.model.User;

public interface UserJpaRepo extends JpaRepository<User, Long> {
}