package com.perfulandia.msvc.comprobante.venta.services;

import com.perfulandia.msvc.comprobante.venta.clients.CarritoClientRest;
import com.perfulandia.msvc.comprobante.venta.clients.ClienteClientRest;
import com.perfulandia.msvc.comprobante.venta.clients.SucursalClientRest;
import com.perfulandia.msvc.comprobante.venta.clients.VendedorClientRest;
import com.perfulandia.msvc.comprobante.venta.dtos.*;
import com.perfulandia.msvc.comprobante.venta.exceptions.ComprobanteException;
import com.perfulandia.msvc.comprobante.venta.models.Carrito;
import com.perfulandia.msvc.comprobante.venta.models.Cliente;
import com.perfulandia.msvc.comprobante.venta.models.Sucursal;
import com.perfulandia.msvc.comprobante.venta.models.Vendedor;
import com.perfulandia.msvc.comprobante.venta.models.entities.Comprobante;
import com.perfulandia.msvc.comprobante.venta.repositories.ComprobanteRepository;

import feign.FeignException;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
//Se agrega el Data para que se usen los métodos de forma automática
public class ComprobanteServiceImpl implements ComprobanteService {

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Autowired
    private ClienteClientRest clienteClientRest;

    @Autowired
    private SucursalClientRest sucursalClientRest;

    @Autowired
    private VendedorClientRest vendedorClientRest;

    @Autowired
    private CarritoClientRest carritoClientRest;


    @Override
    public List<ComprobanteDTO> findAll() {
        return this.comprobanteRepository.findAll().stream().map(comprobante ->{


            Vendedor vendedor = null;
            try{
                vendedor = this.vendedorClientRest.findById(comprobante.getIdVendedor());
            }catch (FeignException ex){
                throw new ComprobanteException("El vendedor buscado no existe");
            }

            Cliente cliente = null;
            try{
                cliente = this.clienteClientRest.findById(comprobante.getIdCliente());
            }catch(FeignException ex){
                throw new ComprobanteException("El cliente buscado no existe");
            }

            Sucursal sucursal = null;
            try{
                sucursal = this.sucursalClientRest.findById(comprobante.getIdComprobante());
            }catch(FeignException ex){
                throw new ComprobanteException("La sucursal buscada no existe");
            }

            Carrito carrito = null;
            try{
                carrito = this.carritoClientRest.findById(comprobante.getIdCarrito());
            }catch (FeignException ex){
                throw new ComprobanteException("El carrito no existe");
            }

            VendedorDTO vendedorDTO = new VendedorDTO();
            vendedorDTO.setRunVendedor(vendedor.getRunVendedor());
            vendedorDTO.setFechaNacimiento(vendedor.getFechaNacimiento());
            vendedorDTO.setNombreCompleto(vendedor.getNombreCompleto());

            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setRunCliente(cliente.getRunCliente());
            clienteDTO.setFechaNacimiento(cliente.getFechaNacimiento());
            clienteDTO.setNombreCompleto(cliente.getNombreCompleto());

            SucursalDTO sucursalDTO = new SucursalDTO();
            sucursalDTO.setNombreSucursal(sucursal.getNombreSucursal());
            sucursalDTO.setDireccionSucursal(sucursal.getDireccionSucursal());

            CarritoDTO carritoDTO = new CarritoDTO();
            carritoDTO.setIdProducto(carrito.getIdProducto());
            carritoDTO.setCantidad(carrito.getCantidad());
            carritoDTO.setCupon(carrito.getCupon());
            carritoDTO.setPrecioTotal(carrito.getPrecioTotal());

            ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
            comprobanteDTO.setVendedor(vendedorDTO);
            comprobanteDTO.setCliente(clienteDTO);
            comprobanteDTO.setSucursal(sucursalDTO);
            comprobanteDTO.setCarrito(carritoDTO);
            comprobanteDTO.setHoraComprobante(comprobante.getHoraComprobante());
            return comprobanteDTO;
        }).toList();
    }

    @Override
    public Comprobante findById(Long id) {
        return this.comprobanteRepository.findById(id).orElseThrow(
                () -> new ComprobanteException("El comprobante con id "+id+" no se encuentra en la base de datos")
        );
    }

    @Override
    public Comprobante save(Comprobante comprobante) {

    try {
        Vendedor vendedor = this.vendedorClientRest.findById(comprobante.getIdVendedor());
        if (vendedor == null) throw new ComprobanteException("El vendedor no existe");

        Cliente cliente = this.clienteClientRest.findById(comprobante.getIdCliente());
        if (cliente == null) throw new ComprobanteException("El cliente no existe");

        Sucursal sucursal = this.sucursalClientRest.findById(comprobante.getIdSucursal());
        if (sucursal == null) throw new ComprobanteException("La sucursal no existe");

    } catch (FeignException ex) {
        throw new ComprobanteException("Error al validar vendedor, cliente o sucursal");
    }

    return this.comprobanteRepository.save(comprobante);
    }

    @Override
    public void deleteById(Long id) {
        if (!comprobanteRepository.existsById(id)){
            throw new ComprobanteException("No se puede eliminar: comprobante no encontrado");
        }
        comprobanteRepository.deleteById(id);
    }

    @Override
    public Comprobante update(Long id, ComprobanteDTO comprobanteDTO) {

        Comprobante comprobante = this.comprobanteRepository.findById(id).orElseThrow(
                () -> new ComprobanteException("El comprobante con id: "+id+" no se encuentra en la base de datos.")
        );

        comprobante.setIdCarrito(comprobante.getIdCarrito());

        Comprobante update = comprobanteRepository.save(comprobante);

        return  update;
    }


//Sol:

// Se agregan validaciones explícitas para verificar la existencia del vendedor, cliente y sucursal
// antes de guardar el comprobante. Esto asegura integridad referencial entre microservicios y permite
// manejar errores específicos en caso de que alguno no exista.


    @Override
    public List<Comprobante> findByClienteId(Long clienteId) {
        return this.comprobanteRepository.findByIdCliente(clienteId);
    }

    @Override
    public List<Comprobante> findByVendedorId(Long vendedorId) {
        return this.comprobanteRepository.findByIdVendedor(vendedorId);
    }

    @Override
    public List<Comprobante> findBySucursalId(Long sucursalId) {
        return this.comprobanteRepository.findByIdSucursal(sucursalId);
    }

    @Override
    public List<Comprobante> findByCarritoId(Long carritoId) {
        return this.comprobanteRepository.findByIdCarrito(carritoId);
    }
}
