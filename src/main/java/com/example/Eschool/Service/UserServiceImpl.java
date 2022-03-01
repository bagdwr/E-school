package com.example.Eschool.Service;

import com.example.Eschool.Entity.Role;
import com.example.Eschool.Entity.User;
import com.example.Eschool.Repository.RoleRepository;
import com.example.Eschool.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        if(!username.isEmpty()){
            return userRepository.findByUsername(username);
        }
        return null;
    }

    @Override
    public User createUser(User user) {
        User checkUser=userRepository.findByUsername(user.getUsername());
        if(checkUser==null){
            Role role=roleRepository.findRoleByName("ROLE_USER");
            if(role!=null){
                List<Role> roleList=new ArrayList<>();
                roleList.add(role);
                user.setRoles(roleList);
                return userRepository.save(user);
            }
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= getUserByUsername(username);
        if(user!=null){
            org.springframework.security.core.userdetails.User securityUser=new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), user.getRoles());
            return securityUser;
        }
        throw new UsernameNotFoundException("User not found");
    }
}
