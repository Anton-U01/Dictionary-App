package com.dictionaryapp.service;


import com.dictionaryapp.model.entity.dto.UserLoginDto;
import com.dictionaryapp.model.entity.dto.UserRegisterDto;

public interface UserService {
    boolean register(UserRegisterDto userRegisterDto);

    boolean login(UserLoginDto userLoginDto);
}
