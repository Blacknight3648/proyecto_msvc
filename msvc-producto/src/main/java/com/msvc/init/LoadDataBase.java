package com.msvc.init;

import com.msvc.model.entity.ProductoModel;
import com.msvc.repository.ProductoRepository;
import com.msvc.services.ProductoService;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;


@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger(LoadDataBase.class); 
    
    
    @Override
    public void run(String... args) throws  Exception{
        Faker faker = new Faker(Locale.of("es", "CL"));
        if (productoRepository.count()==0){
            for (int i=0;i<100;i++){
                ProductoModel productoModel = new ProductoModel();
                productoModel.setPrecio(faker.number().numberBetween(10000, 100000));
                productoModel.setNombre(faker.lorem().word() + " de " + faker.name().firstName());
                productoModel.setDescProducto(String.valueOf(faker.number().numberBetween(10, 90)));




                productoModel = productoRepository.save(productoModel);
                logger.info("El Producto creado es : {}", productoModel.toString());
            }
        }
    }
}
