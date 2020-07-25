package com.mkostadinov.eticketbackend.service;

import com.mkostadinov.eticketbackend.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel createUserIfNotExist(UserServiceModel userServiceModel);
}
