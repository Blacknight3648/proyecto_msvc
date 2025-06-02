package com.perfulandia.msvc.comprobante.venta.repositories;

import com.perfulandia.msvc.comprobante.venta.models.entities.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {

    List<Comprobante> findByIdCliente(Long idCliente);
    List<Comprobante> findByIdVendedor(Long idVendedor);
    List<Comprobante> findByIdSucursal(Long idSucursal);
    List<Comprobante> findByIdCarrito(Long idCarrito);

}
