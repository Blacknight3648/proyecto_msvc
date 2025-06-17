package com.smedinamsvc.resenia.init;

import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.repository.ReseniaRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

/**
 * Clase que carga datos de prueba en la base de datos cuando el perfil activo es 'dev'.
 * Se generan 10,000 reseñas falsas utilizando la biblioteca Faker.
 */
@Profile("dev") // Se ejecuta solo cuando el perfil 'dev' está activo
@Component
public class LoadDatabase implements CommandLineRunner {

    @Autowired
    private ReseniaRepository reseniaRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Override
    public void run(String... args) {
        Faker faker = new Faker(new Locale("es", "CL")); // Faker configurado para español chileno
        Random random = new Random();

        if (reseniaRepository.count() == 0) {
            logger.info("Insertando datos de prueba en la tabla 'resenia'...");

            for (int i = 0; i < 10000; i++) {
                Resenia resenia = new Resenia();

                // Simula IDs de productos y clientes entre 1 y 1000
                resenia.setProductoId((long) faker.number().numberBetween(1, 1000));
                resenia.setIdCliente((long) faker.number().numberBetween(1, 1000));

                // Valoración aleatoria entre 1 y 5
                resenia.setValoracion(faker.number().numberBetween(1, 6));

                // Comentario aleatorio con longitud controlada
                resenia.setResenia(faker.lorem().sentence(10));

                // El campo fechaHoraResenia se autocompleta en @PrePersist
                reseniaRepository.save(resenia);
            }

            logger.info("Carga de datos de prueba completada.");
        } else {
            logger.info("La base de datos ya contiene datos. No se insertaron nuevas reseñas.");
        }
    }
}

