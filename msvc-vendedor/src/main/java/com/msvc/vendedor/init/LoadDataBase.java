package com.msvc.vendedor.init;

import com.msvc.vendedor.models.Vendedor;
import com.msvc.vendedor.repositories.VendedorRepository;
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
    private VendedorRepository vendedorRepository;
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger(LoadDataBase.class); 
    
    
    @Override
    public void run(String... args) throws  Exception{
        Faker faker = new Faker(Locale.of("es", "CL"));
        if (vendedorRepository.count()==0){
            for (int i=0;i<1000;i++){
                Vendedor vendedor = new Vendedor();
                LocalDate fechaNacimiento = LocalDate.now().minusYears(faker.number().numberBetween(18, 80));
                vendedor.setFechaNacimiento(fechaNacimiento);
                vendedor.setNombreCompleto(faker.name().fullName());
                vendedor.setCorreoVendedor(faker.internet().emailAddress());
                vendedor.setEstadoCuenta(faker.bool().bool());

                String numeroString = faker.idNumber().valid().replaceAll("-","");
                String ultimo = numeroString.substring(numeroString.length()-1);
                String restante = numeroString.substring(0,numeroString.length()-1);
                vendedor.setRunVendedor(restante+"-"+ultimo);

                Vendedor = vendedorRepository.save(Vendedor);
                logger.info("El Vendedor creado es : {}", Vendedor.toString());
            }
        }
    }
}
