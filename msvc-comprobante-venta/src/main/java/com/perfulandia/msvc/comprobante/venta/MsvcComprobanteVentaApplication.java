package com.perfulandia.msvc.comprobante.venta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcComprobanteVentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcComprobanteVentaApplication.class, args);
	}

}
