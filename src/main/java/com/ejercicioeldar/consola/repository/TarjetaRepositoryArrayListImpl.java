package com.ejercicioeldar.consola.repository;

import com.ejercicioeldar.consola.model.Tarjeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TarjetaRepositoryArrayListImpl implements TarjetaRepository {
    private List<Tarjeta> tarjetas = new ArrayList<>();

    // Guardar una tarjeta
    public Tarjeta save(Tarjeta tarjeta) {
        if (tarjeta.getId() == null) {
            tarjeta.setId((long) (tarjetas.size() + 1)); // Asignar un ID simple
        }
        tarjetas.add(tarjeta);
        return tarjeta;
    }

    // Método para obtener todas las tarjetas
    public List<Tarjeta> findAll() {
        return tarjetas;
    }

    // Encontrar una tarjeta por marca
    public Optional<Tarjeta> findByMarca(String marca) {
        return tarjetas.stream()
                       .filter(tarjeta -> tarjeta.getMarca().equalsIgnoreCase(marca))
                       .findFirst();
    }
}
