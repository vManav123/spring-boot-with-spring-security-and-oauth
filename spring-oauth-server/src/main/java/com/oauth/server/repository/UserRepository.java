package com.oauth.server.repository;

import com.oauth.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmailAddress(String emailAddress);
}
