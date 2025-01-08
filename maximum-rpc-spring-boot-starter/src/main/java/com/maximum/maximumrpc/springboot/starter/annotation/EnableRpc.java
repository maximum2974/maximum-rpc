package com.maximum.maximumrpc.springboot.starter.annotation;

import com.maximum.maximumrpc.springboot.starter.bootstrap.RpcConsumerBootstrap;
import com.maximum.maximumrpc.springboot.starter.bootstrap.RpcInitBootstrap;
import com.maximum.maximumrpc.springboot.starter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用 Rpc 注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {
    /**
     * 需要启动 server
     * @return
     */
    boolean needServer() default true;
}

