package com.perfulandia.msvc.comprobante.venta.services;

import com.perfulandia.msvc.comprobante.venta.clients.ClienteClientRest;
import com.perfulandia.msvc.comprobante.venta.clients.SucursalClientRest;
import com.perfulandia.msvc.comprobante.venta.clients.VendedorClientRest;
import com.perfulandia.msvc.comprobante.venta.models.entities.Comprobante;
import com.perfulandia.msvc.comprobante.venta.repositories.ComprobanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public List<Comprobante> findAll() {
        return List.of();
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
