package com.shumayac.scaperoom.base;

import java.time.LocalDate;

public class Empresa extends Reserva {
    private boolean factura;

    public Empresa() {
        super();
    }

    public Empresa(String nombre, String apellido, String telefono, String email,
                   int numJugadores, String rangoEdad, LocalDate fechaReserva,
                   String complejidad, boolean factura) {
        super(nombre, apellido, telefono, email, numJugadores, rangoEdad, fechaReserva, complejidad);
        this.factura = factura;
    }

    public boolean isFactura() {
        return factura;
    }

    public void setFactura(boolean factura) {
        this.factura = factura;
    }

    @Override
    public String toString() {
        return "Empresa / " + getNombre() + " " + getApellido() + " / " + getFechaReserva()
                + " / " + getNumJugadores() + " jugadores / " + getComplejidad()
                + " / Factura: " + (factura ? "Sí" : "No");
    }
}