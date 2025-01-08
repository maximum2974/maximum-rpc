package com.maximum.maximumrpc.springboot.starter.bootstrap;

import com.maximum.maximumrpc.RpcApplication;
import com.maximum.maximumrpc.config.RpcConfig;
import com.maximum.maximumrpc.server.tcp.VertxTcpServer;
import com.maximum.maximumrpc.springboot.starter.annotation.EnableRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Rpc 框架启动
 */
@Slf4j
public class RpcInitBootstrap implements ImportBeanDefinitionRegistrar {
    /**
     * Spring初始化时执行，初始化 RPC 框架
     * @param importingClassMetaData
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetaData, BeanDefinitionRegistry registry){
        //获取 EnableRpc 注解的属性值
        boolean needServer = (boolean)importingClassMetaData.getAnnotationAttributes(EnableRpc.class.getName())
                .get("needServer");
        /// RPC 框架初始化（配置和注册中心）
        RpcApplication.init();

        //全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        //启动服务器
        if(needServer){
            VertxTcpServer vertxTcpServer = new VertxTcpServer();
            vertxTcpServer.doStart(rpcConfig.getServerPort());
        }else{
            log.info("不启动 server");
        }
    }
}
