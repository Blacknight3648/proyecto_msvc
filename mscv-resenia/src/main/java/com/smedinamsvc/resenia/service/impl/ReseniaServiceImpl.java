package com.smedinamsvc.resenia.service.impl;

import com.smedinamsvc.resenia.exceptions.ReseniaExceptions;
import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.repository.ReseniaRepository;
import com.smedinamsvc.resenia.service.ReseniaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service

public class ReseniaServiceImpl implements ReseniaService {

    @Autowired
    private ReseniaRepository reseniaRepository;


    //READ
    @Override
    public List<Resenia> findAll(){
        return reseniaRepository.findAll();
    }

    @Override
    public List<Resenia> findByProductoId(Long productoId) throws ReseniaExceptions {
        return List.of();
    }

    @Override
    public Resenia findById(Long id){
        return reseniaRepository.findById(id).orElseThrow(
                ()-> new ReseniaExceptions("La resenia con id " + id + " no se encuentra en la base de datos")
        );
    }

    //CREATE

    @Override
    public Resenia save(Resenia resenia){
        return reseniaRepository.save(resenia);
    }

    //DELETE

    @Override
    public void deleteById(Long id){
    }

    //UPDATE

    @Override
    public Resenia update(Resenia resenia) throws ReseniaExceptions {
        return null;
    }

    @Override
    public Resenia updateById(Resenia resenia) {
        return null;
    }

}


