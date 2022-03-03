package com.example.Eschool.Service;

import com.example.Eschool.Entity.Role;
import com.example.Eschool.Entity.Users;
import com.example.Eschool.Repository.RoleRepository;
import com.example.Eschool.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,BCryptPasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public Users getUserByUsername(String username) {
            return userRepository.findByUsername(username);
    }

    @Override
    public Users createUser(Users users) {
        Users checkUsers =userRepository.findByUsername(users.getUsername());
        if(checkUsers ==null){
            Role role=roleRepository.findRoleByName("ROLE_USER");
            if(role!=null){
                List<Role> roleList=new ArrayList<>();
                roleList.add(role);
                users.setRoles(roleList);
                users.setPassword(passwordEncoder.encode(users.getPassword()));
                return userRepository.save(users);
            }
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = getUserByUsername(username);
        if(users !=null){
            User securityUser=new User(users.getUsername(),
                    users.getPassword(), users.getRoles());
            return securityUser;
        }
        throw new UsernameNotFoundException("User not found");
    }

    @PostConstruct
    public void addValues(){
        Role role=new Role(null,"ROLE_USER");
        roleRepository.save(role);

        List<Users> users= Arrays.asList(
                new Users(null,"user","87777777777","user",null),
                new Users(null,"admin","87777777777","admin",null)
        );

        for(Users u:users){
            createUser(u);
        }
    }
}
