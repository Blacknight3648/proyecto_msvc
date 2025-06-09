package com.smedinamsvc.resenia.init;

import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.repository.ReseniaRepository;
import net.datafaker.Faker;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.logging.Logger;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ReseniaRepository reseniaRepository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(DataLoader.class);


    @Override
    public void run(String... args) throws  Exception{
        Faker faker = new Faker(Locale.of("es", "CL"));
        if (reseniaRepository.count()==0){
            for (int i=0;i<1000;i++){
                Resenia resenia = new Resenia();

                //Crear métodos Faker



                /*
                //cliente.setFechaNacimiento(faker.date().birthdayLocalDate());
                cliente.setNombreCompleto(faker.name().fullName());
                // cliente.setCorreoCliente(faker.);

                String numeroString = faker.idNumber().valid().replaceAll("-","");
                String ultimo = numeroString.substring(numeroString.length()-1);
                String restante = numeroString.substring(0,numeroString.length()-1);
                cliente.setRunCliente(restante+"-"+ultimo);

                 */
            }
        }
    }
}
