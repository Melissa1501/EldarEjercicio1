package com.ejercicioeldar.consola.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;

import java.time.LocalDate;

@Entity
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private LocalDate fechaVencimiento;
    private String cvv;
    private String marca;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @Transient
    private TasaStrategy tasaStrategy;

    public Tarjeta() {
    }
    
    public Tarjeta(String numero, LocalDate fechaVencimiento, String cvv, String marca) {
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.cvv = cvv;
        this.marca = marca;
        asignarEstrategia();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getMarca() {
        return marca.toUpperCase();
    }

    public void setMarca(String marca) {
        this.marca = marca;
        asignarEstrategia();
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @PostLoad
    private void asignarEstrategia() {
        switch (this.marca.toUpperCase()) {
            case "VISA":
                this.tasaStrategy = new TarjetaVisa();
                break;
            case "AMEX":
                this.tasaStrategy = new TarjetaAmex();
                break;
            case "NARA":
                this.tasaStrategy = new TarjetaNara();
                break;
            default:
                throw new IllegalArgumentException("Marca desconocida: " + this.marca);
        }
    }

    public boolean esValidaParaOperacion() {
        return this.fechaVencimiento.isAfter(LocalDate.now());
    }
    
    public double calcularTasa(LocalDate fecha) {
        return tasaStrategy.calcularTasa(fecha);
    }

    @Override
    public String toString() {
        return "- Tarjeta " + this.getMarca() + " finalizada en " + this.getNumero().substring(this.getNumero().length()-4);
    }
}
