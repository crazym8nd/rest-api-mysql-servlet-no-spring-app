package com.vitaly.rest_api_no_spring_app.service.impl;
//  20-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.model.User;
import com.vitaly.rest_api_no_spring_app.repository.UserRepository;
import com.vitaly.rest_api_no_spring_app.repository.impl.UserRepositoryImpl;
import com.vitaly.rest_api_no_spring_app.service.UserService;

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
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public User create(User user) {
        return userRepository.create(user);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
