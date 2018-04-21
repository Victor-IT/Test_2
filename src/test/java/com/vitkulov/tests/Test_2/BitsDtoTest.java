package com.vitkulov.tests.Test_2;

import com.vitkulov.tests.Test_2.dto.BitsDto;
import com.vitkulov.tests.Test_2.dto.UserDto;
import com.vitkulov.tests.Test_2.model.Record;
import com.vitkulov.tests.Test_2.model.User;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;

public class BitsDtoTest {

    private static final ModelMapper modelMapper = new ModelMapper();
    private User user = new User();
    private Record record = new Record();



    @Before
    public void init() {
        user.setId(100L);
        user.setName("Victor");

        record = new Record();
        record.setUplink(512L);
        record.setDownlink(1024L);

        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setRecord(record);
    }

    @Test
    public void mapperRecordToBitsDto() {
        BitsDto bitsDto = modelMapper.map(record, BitsDto.class);

        assertEquals(bitsDto.getDownlink(), Long.valueOf(1024));
        assertEquals(bitsDto.getUplink(), Long.valueOf(512));
    }
}
