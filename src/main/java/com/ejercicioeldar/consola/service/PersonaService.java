package com.ejercicioeldar.consola.service;

import com.ejercicioeldar.consola.model.Persona;
import com.ejercicioeldar.consola.repository.PersonaRepository;

import java.util.List;
import java.util.Optional;

public class PersonaService {
    private PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    // Crear una nueva persona
    public Persona registrarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    // Buscar una persona por DNI
    public Optional<Persona> buscarPersonaPorDni(String dni) {
        return personaRepository.findByDni(dni);
    }

    // Listar todas las personas
    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }

    // Modificar una persona existente
    public Persona actualizarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    // Eliminar una persona
    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }
}
