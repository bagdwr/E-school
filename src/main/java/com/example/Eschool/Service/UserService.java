package com.example.Eschool.Service;

import com.example.Eschool.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);
    User createUser(User user);
}
