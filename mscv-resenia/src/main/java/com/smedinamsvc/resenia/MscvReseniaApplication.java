package com.smedinamsvc.resenia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.smedinamsvc.resenia.client")
public class MscvReseniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscvReseniaApplication.class, args);
	}

}