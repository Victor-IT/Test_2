package com.vitkulov.tests.Test_2;

import com.vitkulov.tests.Test_2.dto.UserDto;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDtoTest {

    private static final UserDto userDto = new UserDto("Victor");

    @Test
    public void toStringTest() {
        System.out.println(userDto.toString());
        assertEquals(userDto.toString(), "UserDto(name=" + userDto.getName() + ")");
    }

}
