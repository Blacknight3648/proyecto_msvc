package com.msvc.carrito.services;

import com.msvc.carrito.repositories.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoServiceImpl implements  CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

}
