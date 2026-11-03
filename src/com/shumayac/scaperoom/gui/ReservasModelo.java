package com.shumayac.scaperoom.gui;

import com.shumayac.scaperoom.base.Cumpleanios;
import com.shumayac.scaperoom.base.Empresa;
import com.shumayac.scaperoom.base.GrupoAmigos;
import com.shumayac.scaperoom.base.Reserva;


import java.time.LocalDate;
import java.util.ArrayList;

public class ReservasModelo {
    private ArrayList<Reserva> listaReservas;

    public ReservasModelo() {
        listaReservas = new ArrayList<>();
    }

    public ArrayList<Reserva> obtenerReservas() {
        return listaReservas;
    }


    //aqui creo el alta del grupo de amigos
    public void altaGrupoAmigos(String nombre, String apellido, String telefono, String email,
                                int numJugadores, String rangoEdad, LocalDate fechaReserva,
                                String complejidad, String nombreEquipo) {
        listaReservas.add(new GrupoAmigos(nombre, apellido, telefono, email,
                numJugadores, rangoEdad, fechaReserva, complejidad, nombreEquipo));
    }

    public void altaEmpresa(String nombre, String apellido, String telefono, String email,
                            int numJugadores, String rangoEdad, LocalDate fechaReserva,
                            String complejidad, boolean factura) {
        listaReservas.add(new Empresa(nombre, apellido, telefono, email,
                numJugadores, rangoEdad, fechaReserva, complejidad, factura));
    }

    public void altaCumpleanios(String nombre, String apellido, String telefono, String email,
                                int numJugadores, String rangoEdad, LocalDate fechaReserva,
                                String complejidad, int edadCumpleaniero) {
        listaReservas.add(new Cumpleanios(nombre, apellido, telefono, email,
                numJugadores, rangoEdad, fechaReserva, complejidad, edadCumpleaniero));
    }

    //falta generar el Xml


}