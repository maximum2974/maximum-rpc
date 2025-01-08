package com.maximum.examplespringbootconsumer;

import com.maximum.example.common.model.User;
import com.maximum.example.common.service.UserService;
import com.maximum.maximumrpc.springboot.starter.annotation.RpcReference;
import org.springframework.stereotype.Service;

@Service
public class ExampleServiceImpl {
    @RpcReference
    private UserService userService;

    public void test(){
        User user = new User();
        user.setName("maximum");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }
}
