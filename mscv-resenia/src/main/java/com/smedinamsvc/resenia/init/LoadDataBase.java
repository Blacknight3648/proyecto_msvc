package com.smedinamsvc.resenia.init;

import com.smedinamsvc.resenia.repository.ReseniaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {

    @Autowired
    private ReseniaRepository reseniaRepository;

    static  final Logger logger = (Logger) LoggerFactory.getLogger(LoadDataBase.class);

    @Override
    public void run(String... args) throws Exception {

    }
}
