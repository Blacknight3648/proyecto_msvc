package com.perfulandia.msvc.comprobante.venta.init;

import com.perfulandia.msvc.comprobante.venta.models.entities.Comprobante;
import com.perfulandia.msvc.comprobante.venta.repositories.ComprobanteRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;

@Profile("dev")
@Component
public class LoadDatabase implements CommandLineRunner {
    @Autowired
    private ComprobanteRepository comprobanteRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es", "cl"));
        if(comprobanteRepository.count()==0){
            for(int i=0;i<100;i++){
                Comprobante comprobante = new Comprobante();

                comprobante.setFactura(faker.bool().bool());
                comprobante.setHoraComprobante(LocalDateTime.now().minusDays(faker.number().numberBetween(0, 30)));

                comprobante.setIdCliente(faker.number().numberBetween(1L, 100L));
                comprobante.setIdVendedor(faker.number().numberBetween(1L, 100L));
                comprobante.setIdSucursal(faker.number().numberBetween(1L,100L));
                comprobante.setIdCarrito(faker.number().numberBetween(1L,100L));

                comprobanteRepository.save(comprobante);
                logger.info("El medico creado es: {}",comprobante.toString());
            }
        }

    }
}
