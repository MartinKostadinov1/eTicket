package com.mkostadinov.eticketbackend.service.impl;

import com.mkostadinov.eticketbackend.model.service.UserServiceModel;
import com.mkostadinov.eticketbackend.repository.UserRepository;
import com.mkostadinov.eticketbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserServiceModel createUserIfNotExist(UserServiceModel userServiceModel) {
        return null;
    }
}
