package com.perfulandia.msvc.comprobante.venta.services;

import com.perfulandia.msvc.comprobante.venta.clients.ClienteClientRest;
import com.perfulandia.msvc.comprobante.venta.clients.SucursalClientRest;
import com.perfulandia.msvc.comprobante.venta.clients.VendedorClientRest;
import com.perfulandia.msvc.comprobante.venta.dtos.ClienteDTO;
import com.perfulandia.msvc.comprobante.venta.dtos.ComprobanteDTO;
import com.perfulandia.msvc.comprobante.venta.dtos.SucursalDTO;
import com.perfulandia.msvc.comprobante.venta.dtos.VendedorDTO;
import com.perfulandia.msvc.comprobante.venta.exceptions.ComprobanteException;
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

            ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
            comprobanteDTO.setVendedor(vendedorDTO);
            comprobanteDTO.setCliente(clienteDTO);
            comprobanteDTO.setSucursal(sucursalDTO);
            return comprobanteDTO;
        }).toList();
    }

    @Override
    public Comprobante findById(Long id) {
        return null;
    }

    @Override
    public Comprobante save(Comprobante comprobante) {
        return null;
    }

    @Override
    public List<Comprobante> findByClienteId(Long clienteId) {
        return List.of();
    }

    @Override
    public List<Comprobante> findByVendedorId(Long vendedorId) {
        return List.of();
    }

    @Override
    public List<Comprobante> findBySucursalId(Long sucursalId) {
        return List.of();
    }
}
