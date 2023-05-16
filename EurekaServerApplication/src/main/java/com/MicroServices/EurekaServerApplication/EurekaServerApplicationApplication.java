package com.MicroServices.EurekaServerApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplicationApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplicationApplication.class, args);
	}
}
