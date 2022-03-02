package com.example.Eschool.Controller;

import com.example.Eschool.Entity.Student;
import com.example.Eschool.Entity.Users;
import com.example.Eschool.Service.StudentService;
import com.example.Eschool.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

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
            Users users =new Users();
            users.setUsername(username);
            users.setPassword(password);
            users.setPhone(phone);
            if(userService.createUser(users)!=null){
                model.addAttribute("Message","You successfully registered");
                return "register";
            }else {
                return "redirect:/login";
            }
        }
        return "redirect:/login";
    }

    private Users getUserData(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            org.springframework.security.core.userdetails.User secuser=( org.springframework.security.core.userdetails.User)authentication.getPrincipal();
            Users users =userService.getUserByUsername(secuser.getUsername());
            return users;
        }

        return null;
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String showProfile(Model model){
        model.addAttribute("user",getUserData());
        model.addAttribute("students",studentService.getAllStudents());
        return "profile";
    }

    @GetMapping("/createStudent")
    @PreAuthorize("isAuthenticated()")
    public String showCreateStudent(Model model){
        model.addAttribute("user",getUserData());
        return "studentCreate";
    }

    @PostMapping("/createStudent")
    @PreAuthorize("isAuthenticated()")
    public String showCreateStudent(
            @RequestParam(name = "firstname")String firstname,
            @RequestParam(name = "surname")String surname,
            @RequestParam(name = "age")Integer age,
            @RequestParam(name = "phone")String phone,
            Model model){
        if(!firstname.trim().isEmpty() && !surname.trim().isEmpty() && age!=null){
            Student student=new Student(null,surname,firstname,phone,age);
            model.addAttribute("Message",studentService.createStudent(student));
        }
        model.addAttribute("user",getUserData());
        return "studentCreate";
    }

}
