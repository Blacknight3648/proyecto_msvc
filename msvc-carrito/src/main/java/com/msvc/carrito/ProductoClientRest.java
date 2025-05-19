package com.msvc.carrito;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "msvc")
public interface ProductoClientRest {

}
