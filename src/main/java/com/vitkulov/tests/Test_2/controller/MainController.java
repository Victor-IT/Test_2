package com.vitkulov.tests.Test_2.controller;

import com.vitkulov.tests.Test_2.model.User;
import com.vitkulov.tests.Test_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/user/{id}")
    public String getUserInfo(Model model, @PathVariable(name = "id") String id) {
        Long userId = Long.parseLong(id);
        User user = userRepository.findOne(userId);
        model.addAttribute("user", user);
        return "info";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/user/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/user/edit")
    public String updateUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(Model model, @PathVariable(name = "id") String id) {
        Long userID = Long.parseLong(id);
        User result = userRepository.findOne(userID);
        model.addAttribute("user", result);
        return "edit";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") String id) {
        Long userID = Long.parseLong(id);
        userRepository.delete(userID);
        return "redirect:/";
    }
}
