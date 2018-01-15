package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.dto.UserDto;
import com.vitkulov.tests.Test_2.model.User;
import com.vitkulov.tests.Test_2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        Page<User> userList = userRepository.findAll(pageable);
        return userList;
    }

    @Override
    public User findOneById(Long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.delete(userId);
    }

    @Override
    public void saveNewUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName()); //TODO: нужен автоматический конвертер
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userRepository.findOne(userDto.getId());
        user.setName(userDto.getName());
        userRepository.save(user);
    }
}
