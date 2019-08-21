package com.leiming.tencent_jdktest.service;

import com.leiming.tencent_jdktest.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
