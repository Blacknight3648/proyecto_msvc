package service.ServiceImpl;

import com.smedina_msvcproveedor.model.Proveedor;
import com.smedina_msvcproveedor.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ProveedorService;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {


    @Autowired
    private ProveedorRepository proveedorRepository;


    @Override
    public List<Proveedor> findAll() {
        return List.of();
    }

    @Override
    public Proveedor findById(Long id) {
        return null;
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return null;
    }

    @Override
    public Proveedor suspend(Proveedor proveedor) {
        return null;
    }
}
