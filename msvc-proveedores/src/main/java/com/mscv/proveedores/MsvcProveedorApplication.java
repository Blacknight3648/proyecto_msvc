package com.mscv.proveedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcProveedorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcProveedorApplication.class, args);
	}

}
