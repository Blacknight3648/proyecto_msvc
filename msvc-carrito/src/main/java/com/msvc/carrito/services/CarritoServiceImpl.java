package com.msvc.carrito.services;

import com.msvc.carrito.clients.ClienteClientRest;
import com.msvc.carrito.clients.ProductoClientRest;
import com.msvc.carrito.clients.VendedorClientRest;
import com.msvc.carrito.dtos.CarritoDTO;
import com.msvc.carrito.dtos.ClienteDTO;
import com.msvc.carrito.dtos.ProductoDTO;
import com.msvc.carrito.dtos.VendedorDTO;
import com.msvc.carrito.exceptions.CarritoException;
import com.msvc.carrito.model.Cliente;
import com.msvc.carrito.model.Producto;
import com.msvc.carrito.model.Vendedor;
import com.msvc.carrito.model.entity.Carrito;
import com.msvc.carrito.repositories.CarritoRespository;
import feign.FeignException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Data
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRespository carritoRespository;

    @Autowired
    private VendedorClientRest vendedorClientRest;

    @Autowired
    private ClienteClientRest clienteClientRest;

    @Autowired
    private ProductoClientRest productoClientRest;

    @Override
    public List<CarritoDTO> findAll() {
        return this.carritoRespository.findAll().stream().map(carrito -> {

            Vendedor vendedor;
            try {
                vendedor = this.vendedorClientRest.findById(carrito.getIdVendedor());
            } catch (FeignException ex) {
                throw new CarritoException("El vendedor buscado no existe");
            }

            Cliente cliente;
            Producto producto;
            try {
                cliente = this.clienteClientRest.findById(carrito.getIdCliente());
                producto = this.productoClientRest.findById(carrito.getIdProducto());
            } catch (FeignException ex) {
                throw new CarritoException("El cliente o el producto no existe en la base de datos");
            }

            VendedorDTO vendedorDTO = new VendedorDTO();
            vendedorDTO.setRunVendedor(vendedor.getRunVendedor());
            vendedorDTO.setNombreCompleto(vendedor.getNombreCompleto());
            vendedorDTO.setFechaNacimiento(vendedor.getFechaNacimiento());
            vendedorDTO.setEstadoCuenta(vendedor.isEstadoCuenta());

            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setRunCliente(cliente.getRunCliente());
            clienteDTO.setNombreCompleto(cliente.getNombreCompleto());
            clienteDTO.setFechaNacimiento(cliente.getFechaNacimiento());
            clienteDTO.setEstadoCuenta(cliente.isEstadoCuenta());

            ProductoDTO productoDTO = new ProductoDTO(); // Se agrega el mapeo del producto al DTO
            productoDTO.setIdProducto(producto.getIdProducto());
            productoDTO.setNombreProducto(producto.getNombreProducto());
            productoDTO.setPrecio(producto.getPrecio());
            productoDTO.setDescProducto(producto.getDescProducto());

            CarritoDTO carritoDTO = new CarritoDTO();
            carritoDTO.setVendedor(vendedorDTO);
            carritoDTO.setCliente(clienteDTO);
            carritoDTO.setProducto(productoDTO); // Se asigna producto al DTO del carrito

            return carritoDTO;
        }).toList();
    }

    @Override
    public Carrito findById(Long id) {
        return this.carritoRespository.findById(id).orElseThrow(
                () -> new CarritoException("El carrito con id: " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public Carrito save(Carrito carrito) {
        try {

            this.clienteClientRest.findById(carrito.getIdCliente());
            Producto producto = this.productoClientRest.findById(carrito.getIdProducto());
            this.vendedorClientRest.findById(carrito.getIdVendedor());

            int precioTotal = producto.getPrecio()*carrito.getCantidad();
            carrito.setPrecioTotal(precioTotal);

        } catch (FeignException ex) {
            throw new CarritoException("Existen problemas con la asociación vendedor, cliente o producto");
        }
        return this.carritoRespository.save(carrito);
    }

    @Override
    public List<Carrito> findByVendedorId(Long vendedorId) {
        return this.carritoRespository.findByIdVendedor(vendedorId);
    }

    @Override
    public void deleteByid(Long id) {
        if (!carritoRespository.existsById(id)){
            throw new CarritoException("No se puede eliminar porque no se ha encontrado el carrito con la id solicitada");
        }
        carritoRespository.deleteById(id);
    }

    @Override
    public Carrito update(Long id, CarritoDTO carritoDTO) {

        Carrito carrito = this.carritoRespository.findById(id).orElseThrow(
                () -> new CarritoException("El carrito con id: "+ id +" no se encuentra en la base de datos")
        );
        carrito.setCantidad(carrito.getCantidad());
        carrito.setCupon(carrito.getCupon());
        carrito.setIdProducto(carrito.getIdProducto());
        carrito.setPrecioTotal(carrito.getPrecioTotal());

        Carrito update = carritoRespository.save(carrito);

        return update;
    }


    @Override
    public List<Carrito> findByClienteId(Long clienteId) {

        return this.carritoRespository.findByIdCliente(clienteId);
    }

}
