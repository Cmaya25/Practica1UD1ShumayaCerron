package com.shumayac.scaperoom.base;

import java.time.LocalDate;

public class Cumpleanios extends Reserva {

    private static final double PACK_DECORACION = 20.0;

    private String nombreCumpleaniero;
    private int edadCumpleaniero;

    public Cumpleanios(String nombreContacto, String telefono, LocalDate fechaReserva, int numJugadores, String nombreCumpleaniero, int edadCumpleaniero) {
        super(nombreContacto, telefono, fechaReserva, numJugadores);
        this.nombreCumpleaniero = nombreCumpleaniero;
        this.edadCumpleaniero = edadCumpleaniero;
    }

    public String getNombreCumpleaniero() {
        return nombreCumpleaniero;
    }

    public void setNombreCumpleaniero(String nombreCumpleaniero) {
        this.nombreCumpleaniero = nombreCumpleaniero;
    }

    public int getEdadCumpleaniero() {
        return edadCumpleaniero;
    }

    public void setEdadCumpleaniero(int edadCumpleaniero) {
        this.edadCumpleaniero = edadCumpleaniero;
    }

    @Override
    public String toString() {
        return super.toString()+" "+nombreCumpleaniero
                +" "+edadCumpleaniero;
    }
}
