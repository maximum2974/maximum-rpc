package com.maximum.example.consumer;

import com.maximum.example.common.model.User;
import com.maximum.example.common.service.UserService;
import com.maximum.maximumrpc.bootstrap.ConsumerBootstrap;
import com.maximum.maximumrpc.proxy.ServiceProxyFactory;

public class ConsumerExample {
    public static void main(String[] args) {
        ConsumerBootstrap.init();

        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("maximum");
        User newUser = userService.getUser(user);
        if(newUser != null){
            System.out.println(newUser.getName());
        }else{
            System.out.println("user == null");
        }
    }
}
