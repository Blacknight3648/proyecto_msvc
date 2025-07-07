package com.mscv.proveedores.init;

import com.mscv.proveedores.model.Proveedor;
import com.mscv.proveedores.Repository.ProveedorRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {

    @Autowired
    private ProveedorRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDataBase.class);

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es", "CL"));

        if (repository.count() == 0) {
            for (int i = 0; i < 50; i++) {
                Proveedor proveedor = new Proveedor();

                // Generar run en formato 12345678-K
                String numeroString = faker.number().digits(8);
                String digitoVerificador = faker.regexify("[0-9kK]");
                String run = numeroString + "-" + digitoVerificador;

                proveedor.setRunProveedor(run);
                proveedor.setRazonSocial(faker.company().name());
                proveedor.setSuspencion(faker.bool().bool());

                proveedor = repository.save(proveedor);
                logger.info("Proveedor creado: {}", proveedor.toString());
            }
        }
    }
}

