package com.ejercicioeldar.consola.model;

import java.time.LocalDate;

public class TarjetaNara implements TasaStrategy {
    @Override
    public double calcularTasa(LocalDate fecha) {
        return (double) fecha.getDayOfMonth() * 0.5;
    }
}

