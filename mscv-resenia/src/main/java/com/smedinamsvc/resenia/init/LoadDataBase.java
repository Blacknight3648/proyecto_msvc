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

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {

    @Autowired
    private ReseniaRepository reseniaRepository;

    static final Logger logger = LoggerFactory.getLogger(LoadDataBase.class);

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker(Locale.of("es", "CL"));
        if (reseniaRepository.count() == 0) {
            for (int i = 0; i < 1000; i++) {
                Resenia resenia = new Resenia();

                Long productoId = (long) faker.number().numberBetween(1, 1000);
                resenia.setProductoId(productoId);

                Long idCliente = (long) faker.number().numberBetween(1, 1000);
                resenia.setIdCliente(idCliente);

                Integer valoracion = faker.number().numberBetween(1, 6);
                resenia.setValoracion(valoracion);

                String comentario = faker.lorem().sentence(20);
                resenia.setResenia(comentario);

                resenia = reseniaRepository.save(resenia);

                logger.info("Reseña creada: {}", resenia.toString());
            }
        }
    }
}

