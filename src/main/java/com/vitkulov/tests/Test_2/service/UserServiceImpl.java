package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.dto.UserDto;
import com.vitkulov.tests.Test_2.model.Record;
import com.vitkulov.tests.Test_2.model.User;
import com.vitkulov.tests.Test_2.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userRepository.findOne(userDto.getId());
        user.setName(userDto.getName());
        userRepository.save(user);
    }

    // обрабатывает список и возвращает его с суммой всех полей uplink/downlink
    @Override
    public List<UserDto> getSumRecords(List<User> users) {
        List<UserDto> result = new ArrayList<>();

        for (User user : users) {
            Long uplink = 0L, downlink = 0L;
            Record sumRecord = new Record();
            UserDto userDto = modelMapper.map(user, UserDto.class);

            for (Record record : user.getRecordList()) {
                uplink += record.getUplink();
                downlink += record.getDownlink();
            }

            sumRecord.setUplink(uplink);
            sumRecord.setDownlink(downlink);
            userDto.setRecord(sumRecord);
            result.add(userDto);
        }

        return result;
    }
}
