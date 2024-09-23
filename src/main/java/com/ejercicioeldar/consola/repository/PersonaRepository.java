package com.ejercicioeldar.consola.repository;

import java.util.List;
import java.util.Optional;

import com.ejercicioeldar.consola.model.Persona;

public interface PersonaRepository {
    public Persona save(Persona persona);

    public List<Persona> findAll();

    public void deleteById(Long id);

    public Optional<Persona> findByDni(String dni);
}
