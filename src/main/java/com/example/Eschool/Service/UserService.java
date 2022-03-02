package com.example.Eschool.Service;

import com.example.Eschool.Entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Users getUserByUsername(String username);
    Users createUser(Users users);
}
