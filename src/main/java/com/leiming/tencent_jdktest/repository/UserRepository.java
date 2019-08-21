package com.leiming.tencent_jdktest.repository;

import com.leiming.tencent_jdktest.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    @Query(value = "select * from user where username = ?1",nativeQuery = true)
    User findByUsername(String username);
}
