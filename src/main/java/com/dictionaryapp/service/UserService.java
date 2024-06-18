package com.dictionaryapp.service;


import com.dictionaryapp.model.entity.dto.UserRegisterDto;

public interface UserService {
    boolean register(UserRegisterDto userRegisterDto);
}
