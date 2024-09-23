package com.ejercicioeldar.consola.repository;

import com.ejercicioeldar.consola.model.Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonaRepositoryArrayListImpl implements PersonaRepository {

    private List<Persona> personas;

    public PersonaRepositoryArrayListImpl() {
        personas = new ArrayList<>();
    }

    // Guardar una persona
    public Persona save(Persona persona) {
        if (persona.getId() == null) {
            persona.setId((long) (personas.size() + 1)); // Asignar un ID simple
        }
        personas.add(persona);
        return persona;
    }

    // Obtener todas las personas
    public List<Persona> findAll() {
        return personas;
    }

    // Eliminar una persona
    public void deleteById(Long id) {
        personas.removeIf(p -> p.getId().equals(id));
    }

    // Encontrar una persona por DNI
    public Optional<Persona> findByDni(String dni) {
        return personas.stream()
                       .filter(persona -> persona.getDni().equals(dni))
                       .findFirst();
    }
}
