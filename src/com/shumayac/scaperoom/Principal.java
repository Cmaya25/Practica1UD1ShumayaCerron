package com.shumayac.scaperoom;

import com.shumayac.scaperoom.gui.ReservasControlador;
import com.shumayac.scaperoom.gui.ReservasModelo;
import com.shumayac.scaperoom.gui.Ventana;

public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        ReservasModelo modelo = new ReservasModelo();
        ReservasControlador controlador = new ReservasControlador(vista, modelo);
    }
}

