package com.holiday.apiusuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiUsuariosApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiUsuariosApplication.class, args);
	}
}
