package com.perfulandia.msvc.comprobante.venta.services;

import com.perfulandia.msvc.comprobante.venta.dtos.ComprobanteDTO;
import com.perfulandia.msvc.comprobante.venta.models.entities.Comprobante;

import java.util.List;

public interface ComprobanteService {
    List<ComprobanteDTO> findAll();
    List<Comprobante> findAllModels();
    Comprobante findById(Long id);
    Comprobante save(Comprobante comprobante);
    Comprobante deleteById(Long id);
    Comprobante update(Long id, ComprobanteDTO comprobanteDTO);
    List<Comprobante> findByClienteId(Long clienteId);
    List<Comprobante> findByVendedorId(Long vendedorId);
    List<Comprobante> findBySucursalId(Long sucursalId);
    List<Comprobante> findByCarritoId(Long carritoId);

}
