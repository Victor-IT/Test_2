package com.vitkulov.tests.Test_2.repository;

import com.vitkulov.tests.Test_2.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
