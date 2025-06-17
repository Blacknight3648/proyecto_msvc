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
public class LoadDatabase implements CommandLineRunner {

    /*
    Se inyecta la información
    del repositorio de manera
    automatica
    */
    @Autowired
    private ReseniaRepository reseniaRepository;

    //Se agregan datos falsos a la base de datos
    private  static  final org.slf4j.Logger logger = (Logger) LoggerFactory.getLogger(LoadDatabase.class);

    @Override
    public void run(String... args) throws Exception {
        //Se define el idioma de la base de datos: (Español y CL es el país: Chile)
        Faker faker = new Faker(Locale.of("es", "CL"));
        /*
        Acá voy a definir los metodos con
        un for y luego todo lo que yo
        quiero generar
        */
        if (reseniaRepository.count() == 0) {
            for (int i=0;i<1000;i++){
                Resenia resenia = new Resenia(); //Se inicia una nueva resenia

                resenia.setProductoId(faker.number().numberBetween(1L, 100L)); // ID del producto ficticio
                resenia.setValoracion(faker.number().numberBetween(1, 5)); // Valoración entre 1 y 5
                resenia.setResenia(faker.lorem().sentence()); // Comentario aleatorio
                resenia.setIdCliente(faker.number().numberBetween(1L, 500L)); // ID cliente ficticio

                // No necesitas setear fechaHoraResenia, se hace automáticamente con @PrePersist

                reseniaRepository.save(resenia); // Guarda en la base de datos

                logger.info("Insertada reseña: {}", resenia.toString());

            }
        }
    }
}
