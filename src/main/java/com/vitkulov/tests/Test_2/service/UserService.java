package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.dto.BitsDto;
import com.vitkulov.tests.Test_2.dto.FilterFormDto;
import com.vitkulov.tests.Test_2.dto.UserDto;
import com.vitkulov.tests.Test_2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<User> getAllUsers(Pageable pageRequest);

    Page<User> findByName(String name, Pageable pageRequest);

    User findOneById(Long userId);

    void saveUser(User user);

    void deleteUserById(Long userId);

    void saveNewUser(UserDto userDto);

    void updateUser(UserDto userDto);

    List<UserDto> getSumRecords(List<User> user);

    UserDto getSumOfUser(User user);

    BitsDto getUserBits(User user, FilterFormDto filterFormDto);
}
