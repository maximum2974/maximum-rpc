package com.maximum.example.provider;

import com.maximum.example.common.service.UserService;
import com.maximum.maximumrpc.RpcApplication;
import com.maximum.maximumrpc.config.RegistryConfig;
import com.maximum.maximumrpc.config.RpcConfig;
import com.maximum.maximumrpc.model.ServiceMetaInfo;
import com.maximum.maximumrpc.registry.LocalRegistry;
import com.maximum.maximumrpc.registry.Registry;
import com.maximum.maximumrpc.registry.RegistryFactory;
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
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        //注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

        try{
            registry.register(serviceMetaInfo);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        //提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
