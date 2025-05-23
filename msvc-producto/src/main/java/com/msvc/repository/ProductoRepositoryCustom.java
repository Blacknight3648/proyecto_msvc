package com.msvc.repository;

import java.util.List;

import com.msvc.model.entity.ProductoModel;

public interface ProductoRepositoryCustom {
    List<ProductoModel> buscarPorCriteriosAvanzados(String nombreParcial, Integer precioMin, Integer precioMax);
}
