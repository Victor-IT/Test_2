package com.vitkulov.tests.Test_2;

import com.vitkulov.tests.Test_2.model.User;
import com.vitkulov.tests.Test_2.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    UserService userService;

	@Test
	public void contextLoads() {
	}

    @Test
    public void getPageableUserListByName() {
        Page<User> userPage = userService.findByName("Польз", new PageRequest(0, 10));
        List<User> users = userPage.getContent();
        System.out.println(users.get(0).getName());

        assertEquals(users.get(0).getName(), "Пользователь 1");
    }
}
