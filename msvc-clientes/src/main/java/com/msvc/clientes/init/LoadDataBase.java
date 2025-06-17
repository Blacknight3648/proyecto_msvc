package com.msvc.clientes.init;

import com.msvc.clientes.models.Cliente;
import com.msvc.clientes.repository.ClienteRepository;
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
    private ClienteRepository clienteRepository;

    private  static  final Logger logger = (Logger) LoggerFactory.getLogger(LoadDataBase.class);

    @Override
    public void run(String... args) throws  Exception{
        Faker faker = new Faker(Locale.of("es", "CL"));
        if (clienteRepository.count()==0){
            for (int i=0;i<1000;i++){
                Cliente cliente = new Cliente();
                LocalDate fechaNacimiento = LocalDate.now().minusYears(faker.number().numberBetween(18, 80));
                cliente.setFechaNacimiento(fechaNacimiento);
                cliente.setNombreCompleto(faker.name().fullName());
                cliente.setCorreoCliente(faker.internet().emailAddress());
                cliente.setEstadoCuenta(faker.bool().bool());

                String numeroString = faker.idNumber().valid().replaceAll("-","");
                String ultimo = numeroString.substring(numeroString.length()-1);
                String restante = numeroString.substring(0,numeroString.length()-1);
                cliente.setRunCliente(restante+"-"+ultimo);

                cliente = clienteRepository.save(cliente);
                logger.info("El cliente creado es : {}", cliente.toString());
            }
        }
    }

}

