package com.example.Eschool.Controller;

import com.example.Eschool.Entity.User;
import com.example.Eschool.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/login")
    public String showLogin(){
        return "login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/register")
    public String showRegister(){
        return "register";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/register")
    public String register(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "password") String password,
            Model model
            ){
        if(!username.isEmpty() && !password.isEmpty() && !phone.isEmpty()){
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setPhone(phone);
            if(userService.createUser(user)!=null){
                model.addAttribute("Message","You successfully registered");
                return "register";
            }else {
                return "redirect:/login";
            }
        }
        return "redirect:/login";
    }
}
