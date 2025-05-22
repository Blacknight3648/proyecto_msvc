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

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> findAll() {
        return this.proveedorRepository.findAll();
    }

    @Override
    public Proveedor findById(Long id){

        return this.proveedorRepository.findById(id).orElseThrow(
                ()-> new ClienteException("El cliente con id "+ id + " no se encuentra en la base de datos")

        );
    }

    @Override
    public Cliente save(Cliente cliente) {
        cliente.setEstadoCuenta(true);
        return clienteRepository.save(cliente);

    }

    @Override
    public Cliente suspend(ClienteDTO clienteDTO) {

        Cliente cliente =  new Cliente();

        boolean estadoCuenta = clienteDTO.isEstadoCuenta();

        return this.clienteRepository.findById(cliente.getIdCliente()).orElseThrow(

                ()->new ClienteException("El cliente con id "+ cliente.getIdCliente() + " no se encuentra la base de datos")

        );
    }
}
