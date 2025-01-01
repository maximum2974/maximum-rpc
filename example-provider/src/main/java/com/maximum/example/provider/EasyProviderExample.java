package com.maximum.example.provider;

import com.maximum.example.common.service.UserService;
import com.maximum.maximumrpc.RpcApplication;
import com.maximum.maximumrpc.registry.LocalRegistry;
import com.maximum.maximumrpc.server.HttpServer;
import com.maximum.maximumrpc.server.VertxHttpServer;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        //RPC 框架初始化
        RpcApplication.init();

        //注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        //提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
