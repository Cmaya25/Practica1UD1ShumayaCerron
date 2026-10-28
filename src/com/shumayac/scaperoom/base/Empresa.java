package com.shumayac.scaperoom.base;

import java.time.LocalDate;

public class Empresa extends Reserva{
    private String nombreEmpresa;
    private boolean necesitaFactura;

    public Empresa(String nombreContacto, String telefono, LocalDate fechaReserva, int numJugadores, String nombreEmpresa, boolean necesitaFactura) {
        super(nombreContacto, telefono, fechaReserva, numJugadores);
        this.nombreEmpresa = nombreEmpresa;
        this.necesitaFactura = necesitaFactura;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public boolean isNecesitaFactura() {
        return necesitaFactura;
    }

    public void setNecesitaFactura(boolean necesitaFactura) {
        this.necesitaFactura = necesitaFactura;
    }

    @Override
    public String toString() {
        return super.toString()+" "+ nombreEmpresa+" "+necesitaFactura;
    }
}
