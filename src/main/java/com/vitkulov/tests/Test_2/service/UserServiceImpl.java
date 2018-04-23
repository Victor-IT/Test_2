package com.vitkulov.tests.Test_2.service;

import com.vitkulov.tests.Test_2.dto.BitsDto;
import com.vitkulov.tests.Test_2.dto.FilterFormDto;
import com.vitkulov.tests.Test_2.dto.UserDto;
import com.vitkulov.tests.Test_2.model.Record;
import com.vitkulov.tests.Test_2.model.User;
import com.vitkulov.tests.Test_2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findByName(String name, Pageable pageRequest) {
        return userRepository.findByNameContainsAllIgnoreCase(name, pageRequest);
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

    // обрабатывает список пользователей и их записи, возвращая UserDTO
    // c единственной записью суммой всех полей uplink/downlink
    @Override
    public List<UserDto> getSumRecords(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();

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
            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    // возвращает UserDTO с единственной записью суммой всех полей uplink/downlink
    // а также с полями начальной и последней даты массива записей
    @Override
    public UserDto getSumOfUser(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        Record sumRecord = new Record();
        Long uplink = 0L, downlink = 0L;

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();

        for (Record record : user.getRecordList()) {
            uplink += record.getUplink();
            downlink += record.getDownlink();

            LocalDateTime recordTime = record.getDate();
            if (recordTime.isBefore(startDate)) startDate = recordTime;
            if (recordTime.isAfter(endDate)) endDate = recordTime;
        }

        sumRecord.setUplink(uplink);
        sumRecord.setDownlink(downlink);
        userDto.setRecord(sumRecord);
        userDto.setStartDate(startDate);
        userDto.setEndDate(endDate);

        return userDto;
    }

    // заполняем обеъкт BitsDTO данными: длительность и суммы входящщих и исходящих данных
    public BitsDto getUserBits(User user, FilterFormDto filterFormDto) {

        UserDto userDto = getSumOfUser(user);
        BitsDto bitsDto = modelMapper.map(userDto.getRecord(), BitsDto.class);
        LocalDateTime start = null;
        LocalDateTime end = null;

        // парсим начальную и конечную даты из полей ввода дат формы
        try {
            start = LocalDateTime.parse(filterFormDto.getStartDate());
        } catch (Exception ex) {
            log.info("startDate " + ex.getMessage());
        }
        try {
            end = LocalDateTime.parse(filterFormDto.getEndDate());
        } catch (Exception ex) {
            log.info("endDate " + ex.getMessage());
        }

        // если даты были пусты, берем даты из записей пользователя
        if (start == null) {
            start = userDto.getStartDate();
        }
        if (end == null) {
            end = userDto.getEndDate();
        }

        Duration duration = Duration.between(start, end);
        bitsDto.setDuration(duration.getSeconds());
        return bitsDto;
    }
}
