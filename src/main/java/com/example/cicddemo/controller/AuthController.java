package com.example.cicddemo.controller;

import com.example.cicddemo.model.User;
import com.example.cicddemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("username", username);
            return "dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}