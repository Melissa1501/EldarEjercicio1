package com.ejercicioeldar.consola.service;

import com.ejercicioeldar.consola.model.Persona;
import com.ejercicioeldar.consola.model.Tarjeta;
import com.ejercicioeldar.consola.repository.PersonaRepository;
import com.ejercicioeldar.consola.repository.TarjetaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TarjetaService {
    private TarjetaRepository tarjetaRepository;
    private PersonaRepository personaRepository;

    public TarjetaService(TarjetaRepository tarjetaRepository, PersonaRepository personaRepository) {
        this.tarjetaRepository = tarjetaRepository;
        this.personaRepository = personaRepository;
    }

    // Registrar una nueva tarjeta
    public Tarjeta registrarTarjeta(String dni, Tarjeta tarjeta) {
        Persona persona = personaRepository.findByDni(dni)
                            .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con DNI: " + dni));

        // Asociar la tarjeta a la persona
        tarjeta.setPersona(persona);
        persona.asociarTarjeta(tarjeta);

        return tarjetaRepository.save(tarjeta);
    }

    // Buscar todas las tarjetas de una persona por su DNI
    public List<Tarjeta> buscarTarjetasPorDni(String dni) {
        Persona persona = personaRepository.findByDni(dni)
                            .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con DNI: " + dni));

        return persona.getTarjetas();
    }

    // Buscar tarjeta por su marca
    public Optional<Tarjeta> buscarTarjetaPorMarca(String marca) {
        return tarjetaRepository.findByMarca(marca);
    }

    // Calcular la tasa según la tarjeta
    public double calcularTasa(Tarjeta tarjeta, LocalDate fecha) {
        return tarjeta.calcularTasa(fecha);
    }

    public List<String> consultarTasasPorFecha(LocalDate fecha) {
        // Si la fecha es nula, usar la fecha actual
        LocalDate fechaFinal = (fecha == null) ? LocalDate.now() : fecha;
    
        // Obtener todas las tarjetas
        List<Tarjeta> tarjetas = tarjetaRepository.findAll();
    
        // Calcular tasas para cada tarjeta y devolver los resultados en una lista
        return tarjetas.stream()
                       .map(tarjeta -> tarjeta.getMarca() + " - Tasa: " + tarjeta.calcularTasa(fechaFinal))
                       .collect(Collectors.toList());
    }

    // Consultar si una tarjeta es válida para operar
    public boolean esValidaParaOperacion(Tarjeta tarjeta, double monto, String cvv) {
        return tarjeta.esValidaParaOperacion();
    }

    public List<Tarjeta> listarTarjetas() {
        return tarjetaRepository.findAll();
    }
}
