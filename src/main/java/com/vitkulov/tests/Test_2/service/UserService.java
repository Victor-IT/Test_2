package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
}
