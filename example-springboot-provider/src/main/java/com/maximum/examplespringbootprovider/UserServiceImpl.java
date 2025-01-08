package com.maximum.examplespringbootprovider;

import com.maximum.example.common.model.User;
import com.maximum.example.common.service.UserService;
import com.maximum.maximumrpc.springboot.starter.annotation.RpcService;
import org.springframework.stereotype.Service;

@Service
@RpcService
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名: " + user.getName());
    }
}
