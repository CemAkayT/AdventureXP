package com.example.adventure.controller;

import com.example.adventure.model.User;
import com.example.adventure.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserControllerHTML {

    private UserService uService;

    public UserControllerHTML(UserService uService){
        this.uService = uService;
    }

    @PostMapping("/adduser")
    public String addUser(User user, Model model){
        uService.save(user);
        model.addAttribute("user", user);
            return "redirect:/index";
        }
    @GetMapping("/index")
    public String showUserList(Model model) {
        Set<User> users = new HashSet<>(uService.findAll());
        model.addAttribute("users", users);
        return "index";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = uService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "update-user";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        uService.save(user);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = uService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        uService.delete(user);
        return "redirect:/index";
    }

}

