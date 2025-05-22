package service;

import com.smedina_msvcproveedor.model.Proveedor;

import java.util.List;

public interface ProveedorService {

    List<Proveedor> findAll();
    Proveedor findById(Long id);
    Proveedor save(Proveedor proveedor);
    Proveedor suspend(Proveedor proveedor);

}
