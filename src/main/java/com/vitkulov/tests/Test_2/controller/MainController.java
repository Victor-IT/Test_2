package com.vitkulov.tests.Test_2.controller;

import com.vitkulov.tests.Test_2.model.User;
import com.vitkulov.tests.Test_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/create")
    public String addNewUser(@RequestBody User user) {
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping
    public String getIndex() {
        return "index";
    }
}
