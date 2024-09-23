package com.ejercicioeldar.consola.model;

import java.time.LocalDate;

public interface TasaStrategy {
    double calcularTasa(LocalDate fecha);
}
