package com.shumayac.scaperoom.base;

import java.time.LocalDate;

public class Cumpleanios extends Reserva {
    private int edadCumpleaniero;

    public Cumpleanios() {
        super();
    }

    public Cumpleanios(String nombre, String apellido, String telefono, String email,
                       int numJugadores, String rangoEdad, LocalDate fechaReserva,
                       String complejidad, int edadCumpleaniero) {
        super(nombre, apellido, telefono, email, numJugadores, rangoEdad, fechaReserva, complejidad);
        this.edadCumpleaniero = edadCumpleaniero;
    }

    public int getEdadCumpleaniero() {
        return edadCumpleaniero;
    }

    public void setEdadCumpleaniero(int edadCumpleaniero) {
        this.edadCumpleaniero = edadCumpleaniero;
    }

    @Override
    public String toString() {
        return "Cumpleaños / " + getNombre() + " " + getApellido() + " / " + getFechaReserva()
                + " / " + getNumJugadores() + " jugadores / " + getComplejidad()
                + " / Cumpleañero: " + edadCumpleaniero + " años";
    }
}
