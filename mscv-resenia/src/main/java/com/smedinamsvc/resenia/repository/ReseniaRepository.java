package com.smedinamsvc.resenia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smedinamsvc.resenia.model.Resenia;

@Repository
public interface ReseniaRepository extends JpaRepository<Resenia, Long> {

}