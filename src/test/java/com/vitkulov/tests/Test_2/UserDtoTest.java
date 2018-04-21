package com.vitkulov.tests.Test_2;

import com.vitkulov.tests.Test_2.dto.UserDto;
import com.vitkulov.tests.Test_2.model.User;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;

public class UserDtoTest {

    private static final UserDto userDto = new UserDto("Victor");
    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void toStringTest() {
        System.out.println(userDto.toString());
        assertEquals(userDto.toString(), "UserDto(name=" + userDto.getName() + ")");
    }

    @Test
    public void checkUserMapping() {
        User user = new User();
        user.setName("Vasya");
        user.setId(15L);

        UserDto userDto = modelMapper.map(user, UserDto.class);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());

        userDto = new UserDto();
        userDto.setName("Ivan");
        userDto.setId(21L);

        user = modelMapper.map(userDto, User.class);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getName());
    }

}
