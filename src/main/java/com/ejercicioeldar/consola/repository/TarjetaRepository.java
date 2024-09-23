package com.ejercicioeldar.consola.repository;

import java.util.List;
import java.util.Optional;

import com.ejercicioeldar.consola.model.Tarjeta;

public interface TarjetaRepository {
    public Tarjeta save(Tarjeta tarjeta);

    public List<Tarjeta> findAll();

    public Optional<Tarjeta> findByMarca(String marca);
}
