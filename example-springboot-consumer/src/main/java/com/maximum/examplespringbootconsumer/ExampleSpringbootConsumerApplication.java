package com.maximum.examplespringbootconsumer;

import com.maximum.maximumrpc.springboot.starter.annotation.EnableRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRpc(needServer = false)
public class ExampleSpringbootConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleSpringbootConsumerApplication.class, args);
	}

}
