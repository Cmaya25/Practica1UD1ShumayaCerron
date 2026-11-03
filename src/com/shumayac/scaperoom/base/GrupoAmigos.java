package com.shumayac.scaperoom.base;

import java.time.LocalDate;

public class GrupoAmigos extends Reserva {
    private String nombreEquipo;

    public GrupoAmigos() {
        super();
    }

    public GrupoAmigos(String nombre, String apellido, String telefono, String email,
                       int numJugadores, String rangoEdad, LocalDate fechaReserva,
                       String complejidad, String nombreEquipo) {
        super(nombre, apellido, telefono, email, numJugadores, rangoEdad, fechaReserva, complejidad);
        this.nombreEquipo = nombreEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    @Override
    public String toString() {
        return "Amigos / " + getNombre() + " " + getApellido() + " / " + getFechaReserva()
                + " / " + getNumJugadores() + " jugadores / " + getComplejidad()
                + " / Equipo: " + nombreEquipo;
    }
}
