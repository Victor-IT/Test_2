package com.vitkulov.tests.Test_2.controller;

import com.vitkulov.tests.Test_2.dto.PageWrapper;
import com.vitkulov.tests.Test_2.dto.UserDto;
import com.vitkulov.tests.Test_2.model.User;
import com.vitkulov.tests.Test_2.repository.UserRepository;
import com.vitkulov.tests.Test_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getAll(Model model, Pageable pageable) {
        Page<User> userPage = userService.getAllUsers(pageable);
        PageWrapper<User> page = new PageWrapper<>(userPage, "/");
        model.addAttribute("users", page.getContent());
        model.addAttribute("page", page);
        return "views/index";
    }

    @GetMapping("/user/{id}")
    public String getUserInfo(Model model, @PathVariable(name = "id") String id) {
        Long userId = Long.parseLong(id);
        User user = userRepository.findOne(userId);
        model.addAttribute("user", user);
        return "views/info";
    }

    @PostMapping("/user/new")
    public String addUser(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "views/new";
        }

        User user = new User(); //TODO: нужен сервисный слой
        user.setName(userDto.getName()); //TODO: нужен автоматический конвертер
        userRepository.save(user);
        return "redirect:/";
    }

    @GetMapping("/user/new")
    public String newUser(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "views/new";
    }

    @PostMapping("/user/edit")
    public String updateUser(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "views/edit";
        }

        User user = new User();
        user.setName(userDto.getName());
        userRepository.save(user);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(Model model, @PathVariable(name = "id") String id) {
        Long userID = Long.parseLong(id);
        User result = userRepository.findOne(userID);
        model.addAttribute("userDto", result);
        return "views/edit";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") String id) {
        Long userID = Long.parseLong(id);
        userRepository.delete(userID);
        return "redirect:/";
    }
}
