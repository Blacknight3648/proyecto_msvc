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
import java.util.Random;

@Profile("dev")
@Component
public class LoadDatabase implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es", "CL"));
        Random random = new Random();

        if (clienteRepository.count() == 0) {
            for (int i = 0; i < 10000; i++) {
                Cliente cliente = new Cliente();

                // Nombre completo
                cliente.setNombreCompleto(faker.name().fullName());

                // RUN cliente simulado (11111111-K)
                String numeroString = faker.number().digits(8);
                String dv = faker.regexify("[0-9Kk]");
                cliente.setRunCliente(numeroString + "-" + dv);

                // Fecha de nacimiento entre 18 y 70 años
                cliente.setFechaNacimiento(LocalDate.now().minusYears(18 + random.nextInt(53)));

                // Correo electrónico
                cliente.setCorreoCliente(faker.internet().emailAddress());

                // Estado de cuenta aleatorio
                cliente.setEstadoCuenta(random.nextBoolean());

                // Guardar en la base de datos
                clienteRepository.save(cliente);
                logger.info("Cliente creado: {}", cliente);
            }
        }
    }
}

