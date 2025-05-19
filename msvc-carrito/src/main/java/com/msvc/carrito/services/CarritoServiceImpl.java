package com.msvc.carrito.services;

import com.msvc.carrito.ProductoClientRest;
import com.msvc.carrito.clients.ClienteClientRest;
import com.msvc.carrito.clients.VendedorClientRest;
import com.msvc.carrito.dtos.CarritoDTO;
import com.msvc.carrito.dtos.VendedorDTO;
import com.msvc.carrito.exceptions.CarritoException;
import com.msvc.carrito.model.Cliente;
import com.msvc.carrito.model.Producto;
import com.msvc.carrito.model.Vendedor;
import com.msvc.carrito.model.entity.Carrito;
import com.msvc.carrito.repositories.CarritoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoServiceImpl implements  CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ClienteClientRest clienteClientRest;

    @Autowired
    private VendedorClientRest vendedorClientRest;

    @Autowired
    private ProductoClientRest productoClientRest;

    @Override
    public List<CarritoDTO> findAll(){
        return this.carritoRepository.findAll().stream().map(   carrito -> {

            Vendedor vendedor = null;
            try {
                vendedor = this.vendedorClientRest.findById(carrito.getIdVendedor());
            }catch (FeignException ex) {
                throw new CarritoException("El vendedor buscado no existe");
            }

            VendedorDTO vendedorDTO = new VendedorDTO();
            vendedorDTO.setRunVendedor(vendedor.getRunVendedor());
            vendedorDTO.setNombreCompleto(vendedor.getNombreCompleto());

            V


            Cliente cliente = null;
        }).toList();
    }

    @Override
    public Carrito findById(Long id) {
        return null;
    }

    @Override
    public Carrito save(Carrito carrito) {
        return null;
    }

    @Override
    public List<Carrito> findByProductoId(Long productoId) {
        return List.of();
    }

    @Override
    public List<Carrito> findByVendedorId(Long vendedorId) {
        return List.of();
    }

}
