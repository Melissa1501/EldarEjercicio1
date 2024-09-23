package com.ejercicioeldar.consola.model;

import java.time.LocalDate;

public class TarjetaVisa implements TasaStrategy {
    @Override
    public double calcularTasa(LocalDate fecha) {
        return (double) fecha.getYear() / fecha.getMonthValue();
    }
}
