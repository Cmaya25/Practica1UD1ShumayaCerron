package com.shumayac.scaperoom.base;

import java.time.LocalDate;

public class GrupoAmigos extends Reserva{
    private String nombreEquipo;

    public GrupoAmigos(String nombreContacto, String telefono, LocalDate fechaReserva, int numJugadores, String nombreEquipo) {
        super(nombreContacto, telefono, fechaReserva, numJugadores);
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
        return super.toString()+
                " " + nombreEquipo;
    }

}
