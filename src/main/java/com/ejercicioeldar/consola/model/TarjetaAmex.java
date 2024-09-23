package com.ejercicioeldar.consola.model;

import java.time.LocalDate;

public class TarjetaAmex implements TasaStrategy {
    @Override
    public double calcularTasa(LocalDate fecha) {
        return (double) fecha.getMonthValue() * 0.1;
    }
}
