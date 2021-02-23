package me.rimsan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private  UserRepository repo;

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String viewSignUpForm(Model model){

        model.addAttribute("user", new User());

        return "forms/signup";
    }

    @PostMapping("/process_register")
    public String processRegister(User user){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repo.save(user);
        return "forms/signup_success";
    }


    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = repo.findAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }
}
