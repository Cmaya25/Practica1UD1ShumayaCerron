package com.shumayac.scaperoom.base;

import java.time.LocalDate;

public abstract class Reserva {

    public static final double PRECIO_PERSONA = 15.0;

    private String nombreContacto;
    private String apellidoContacto;
    private String telefono;
    private String email;
    private String rangoEdad;
    private int numJugadores;
    private LocalDate fechaReserva;
    public Reserva(String nombreContacto, String telefono, LocalDate fechaReserva, int numJugadores) {
        this.nombreContacto = nombreContacto;
        this.telefono = telefono;
        this.fechaReserva = fechaReserva;
        this.numJugadores = numJugadores;
    }

    public static double getPrecioPersona() {
        return PRECIO_PERSONA;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    @Override
    public String toString() {
        return "Reserva : "+ nombreContacto + " " +
                " " + telefono +
                " " + fechaReserva +
                " " + numJugadores;
    }
}
