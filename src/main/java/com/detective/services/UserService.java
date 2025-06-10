package com.detective.services;

import com.detective.models.User;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(Long id);
    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    void deleteById(Long id);
} 