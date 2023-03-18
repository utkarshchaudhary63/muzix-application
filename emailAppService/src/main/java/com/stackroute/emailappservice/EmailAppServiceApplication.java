package com.stackroute.emailappservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class  EmailAppServiceApplication{

	public static void main(String[] args) {
		SpringApplication.run(EmailAppServiceApplication.class, args);
	}

}
