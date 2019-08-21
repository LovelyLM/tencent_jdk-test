package com.leiming.tencent_jdktest.service.impl;

import com.leiming.tencent_jdktest.entity.User;
import com.leiming.tencent_jdktest.repository.UserRepository;
import com.leiming.tencent_jdktest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
