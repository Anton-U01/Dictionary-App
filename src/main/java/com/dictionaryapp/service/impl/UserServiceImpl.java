package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.entity.dto.UserLoginDto;
import com.dictionaryapp.model.entity.dto.UserRegisterDto;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.CurrentUser;
import com.dictionaryapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public boolean register(UserRegisterDto userRegisterDto) {
        if(!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
            return false;
        }
        Optional<User> optionalUser = userRepository.findByEmailOrUsername(userRegisterDto.getEmail(), userRegisterDto.getUsername());
        if(optionalUser.isPresent()){
            return false;
        }
        User user = modelMapper.map(userRegisterDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return true;
    }

    @Override
    public boolean login(UserLoginDto userLoginDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userLoginDto.getUsername());
        if(optionalUser.isEmpty()){
            return false;
        }
        User user = optionalUser.get();

        if(!passwordEncoder.matches(userLoginDto.getPassword(),user.getPassword())){
            return false;
        }

        currentUser.login(user);

        return true;
    }

    @Override
    public void logout() {
        currentUser.logout();
    }
}
