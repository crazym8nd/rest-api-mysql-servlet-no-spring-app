package com.vitaly.rest_api_no_spring_app.service.impl;
//  20-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.dto.UserDto;
import com.vitaly.rest_api_no_spring_app.model.User;
import com.vitaly.rest_api_no_spring_app.repository.UserRepository;
import com.vitaly.rest_api_no_spring_app.repository.impl.UserRepositoryImpl;
import com.vitaly.rest_api_no_spring_app.service.UserService;
import com.vitaly.rest_api_no_spring_app.util.mappers.UserMapper;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserServiceImpl(){
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public List<UserDto> getAll() {
            List<User> users = userRepository.getAll();
            List<UserDto> userDtoList = new ArrayList<>();
            for (User user : users) {
                UserDto userDto = UserMapper.convertEntityToDtoWithoutEvents(user);
                userDtoList.add(userDto);
            }
            return userDtoList;

    }

    @Override
    public UserDto getById(Integer integer) {
        User user = userRepository.getById(integer);
        return UserMapper.convertEntityToDtoWithoutEvents(user);
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = UserMapper.convertDtoToEntityWithoutEvents(userDto);
        userRepository.create(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = UserMapper.convertDtoToEntityWithoutEvents(userDto);
        userRepository.update(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public void deleteById(Integer integer) {
        userRepository.deleteById(integer);

    }
}
