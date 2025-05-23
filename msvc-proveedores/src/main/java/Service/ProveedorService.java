package Service;

import java.util.List;

import com.mscv.proveedores.DTO.ProveedorDTO;

public interface ProveedorService {

    List<ProveedorDTO> findAll();
    ProveedorDTO findById(Long id);
    ProveedorDTO save(ProveedorDTO proveedorDTO);
    ProveedorDTO suspend(Long id); // ejemplo si quieres suspender o eliminar lógicamente
}

