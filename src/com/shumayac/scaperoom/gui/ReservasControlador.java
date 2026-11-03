package com.shumayac.scaperoom.gui;

import com.shumayac.scaperoom.base.Cumpleanios;
import com.shumayac.scaperoom.base.Empresa;
import com.shumayac.scaperoom.base.GrupoAmigos;
import com.shumayac.scaperoom.base.Reserva;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

public class ReservasControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private ReservasModelo modelo;
    private File ultimaRutaExportada;

    public ReservasControlador(Ventana vista, ReservasModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No se encontro el archivo " + e.getMessage());
        }
        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
        actualizarCamposSegunTipo();
    }

    //para saber si hay campos vacios

    private boolean hayCamposVacios() {
        if (vista.nombreTxt.getText().isEmpty() ||
                vista.apellidoTxt.getText().isEmpty() ||
                vista.telefonoTxt.getText().isEmpty() ||
                vista.emailTxt.getText().isEmpty() ||
                vista.datePicker.getText().isEmpty()) {
            return true;
        }
        // solo para los campos de cada radio
        if (vista.grupoAmigosRB.isSelected()) {
            return vista.nombreEquipoTxt.getText().trim().isEmpty();
        }
        if (vista.cumpleaniosRB.isSelected()) {
            return vista.edadTxt.getText().trim().isEmpty();
        }
        return false;
    }

    private void limpiarCampos() {
        vista.nombreTxt.setText(null);
        vista.apellidoTxt.setText(null);
        vista.telefonoTxt.setText(null);
        vista.emailTxt.setText(null);
        vista.spinner2.setValue(3);
        vista.comboBox1.setSelectedIndex(0);
        vista.complejidadCb.setSelectedIndex(0);
        vista.datePicker.clear();
        vista.siCheckBox.setSelected(false);
        vista.edadTxt.setText(null);
        vista.nombreEquipoTxt.setText(null);
        vista.nombreTxt.requestFocus();
    }

    public void refrescar() {
        vista.dlmReserva.clear();
        for (Reserva unaReserva : modelo.obtenerReservas()) {
            vista.dlmReserva.addElement(unaReserva);
        }
    }

    //mostramos los campos de cada radio button
    private void actualizarCamposSegunTipo() {
        boolean amigos = vista.grupoAmigosRB.isSelected();
        boolean empresa = vista.empresasRB.isSelected();
        boolean cumple = vista.cumpleaniosRB.isSelected();

        vista.nombreEquipoLbl.setVisible(amigos);
        vista.nombreEquipoTxt.setVisible(amigos);

        vista.facturaLbl.setVisible(empresa);
        vista.siCheckBox.setVisible(empresa);

        vista.edadLbl.setVisible(cumple);
        vista.edadTxt.setVisible(cumple);
    }

    //comprobar el radiobutton que esta seleccionado (usamos instance of para saber de que tipo es )
    private boolean tipoReserva(Reserva r) {
        if (r instanceof Empresa) {
            return vista.empresasRB.isSelected();
        }
        if (r instanceof GrupoAmigos) {
            return vista.grupoAmigosRB.isSelected();
        }
        else  {
        return vista.cumpleaniosRB.isSelected();}
    }

    //si no añade un numero me añade un -1
    private int leerEdad() {
        try {
            return Integer.parseInt(vista.edadTxt.getText().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    //validar edad
    private boolean edadInvalida() {
        if (!vista.cumpleaniosRB.isSelected()) {
            return false;
        }
        int edad = leerEdad();
        return edad < 1 || edad > 100;
    }
    //controlado desde el spiner y sus propiedades

    // El numero de jugadores lo hemos controlado desde el spiner
   /* private boolean jugadoresInvalidos(int jugadores) {
        return jugadores < Reserva.MIN_JUGADORES || jugadores > Reserva.MAX_JUGADORES;
    }*/

    /*private String mensajeJugadores() {
        return "El numero de jugadores debe estar entre " + Reserva.MIN_JUGADORES
                + " y " + Reserva.MAX_JUGADORES;
    }*/


    //listener de los botones y radio
    /*falta botones y el xml  */
    private void addActionListener(ActionListener listener) {
        vista.cumpleaniosRB.setActionCommand("Cumpleanios"); // evita problemas con la ñ
        vista.nuevoButton.addActionListener(listener);
        vista.actualizarButton.addActionListener(listener);
        vista.eliminarButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.grupoAmigosRB.addActionListener(listener);
        vista.empresasRB.addActionListener(listener);
        vista.cumpleaniosRB.addActionListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    //fichero de configuracion

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("reservas.conf"));
        String ruta = configuracion.getProperty("ultimaRutaExportada");
        if (ruta != null) {
            ultimaRutaExportada = new File(ruta);
        }
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    private void guardarDatosConfiguracion() throws IOException {
        if (ultimaRutaExportada == null) {
            return;
        }
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        PrintWriter escritor = new PrintWriter("reservas.conf");
        configuracion.store(escritor, "Datos configuracion reservas");
        escritor.close();
    }

    // botones y radios funcionalidad

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Hay campos vacios.\nRellena todos los campos visibles.");
                    break;
                }
                if (vista.datePicker.getDate() == null) {
                    Util.mensajeError("La fecha de reserva no es valida");
                    break;
                }
               int jugadores = (Integer) vista.spinner2.getValue();

                if (edadInvalida()) {
                    Util.mensajeError("La edad del cumpleañero debe ser un numero entero entre 1 y 100");
                    break;
                }

                String rango = vista.comboBox1.getSelectedItem().toString();
                String complejidad = vista.complejidadCb.getSelectedItem().toString();

                if (vista.empresasRB.isSelected()) {
                    modelo.altaEmpresa(vista.nombreTxt.getText(), vista.apellidoTxt.getText(),
                            vista.telefonoTxt.getText(), vista.emailTxt.getText(), jugadores, rango,
                            vista.datePicker.getDate(), complejidad,
                            vista.siCheckBox.isSelected());
                } else if (vista.cumpleaniosRB.isSelected()) {
                    modelo.altaCumpleanios(vista.nombreTxt.getText(), vista.apellidoTxt.getText(),
                            vista.telefonoTxt.getText(), vista.emailTxt.getText(), jugadores, rango,
                            vista.datePicker.getDate(), complejidad, leerEdad());
                } else {
                    modelo.altaGrupoAmigos(vista.nombreTxt.getText(), vista.apellidoTxt.getText(),
                            vista.telefonoTxt.getText(), vista.emailTxt.getText(), jugadores, rango,
                            vista.datePicker.getDate(), complejidad,
                            vista.nombreEquipoTxt.getText().trim());
                }
                limpiarCampos();
                refrescar();
                break;

            case "Actualizar":
                Reserva seleccionada = (Reserva) vista.list1.getSelectedValue();
                if (seleccionada == null) {
                    Util.mensajeError("Selecciona una reserva de la lista");
                    break;
                }
                if (hayCamposVacios() || vista.datePicker.getDate() == null) {
                    Util.mensajeError("Los campos no pueden estar vacios y la fecha debe ser valida");
                    break;
                }
                if (!tipoReserva(seleccionada)) {
                    Util.mensajeError("No se puede cambiar el tipo de una reserva.\n" +
                            "Elimina la reserva y crea una nueva.");
                    break;
                }
               int jugadoresAct = (Integer) vista.spinner2.getValue();

                if (edadInvalida()) {
                    Util.mensajeError("La edad del cumpleañero debe ser un numero entero entre 1 y 120");
                    break;
                }


            case "Eliminar":
                Reserva aEliminar = (Reserva) vista.list1.getSelectedValue();
                if (aEliminar == null) {
                    Util.mensajeError("Selecciona una reserva de la lista");
                    break;
                }



            case "Grupo Amigos":
            case "Empresas":
            case "Cumpleanios":
                // al cambiar de tipo se vacian los campos de cada radio y se muestran solo los del nuevo tipo
                vista.edadTxt.setText(null);
                vista.nombreEquipoTxt.setText(null);
                vista.siCheckBox.setSelected(false);
                actualizarCamposSegunTipo();
                break;
        }
    }

    // cambiar cada radio y mostrar sus campos

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //volver a este trozo de codigo !!!
        if (!e.getValueIsAdjusting()) {
            Reserva r = (Reserva) vista.list1.getSelectedValue();
            if (r == null) {
                return;
            }
            vista.nombreTxt.setText(r.getNombre());
            vista.apellidoTxt.setText(r.getApellido());
            vista.telefonoTxt.setText(r.getTelefono());
            vista.emailTxt.setText(r.getEmail());
            vista.spinner2.setValue(r.getNumJugadores());
            vista.comboBox1.setSelectedItem(r.getRangoEdad());
            vista.datePicker.setDate(r.getFechaReserva());
            vista.complejidadCb.setSelectedItem(r.getComplejidad());

            // primero el doClick (vacia los campos propios y cambia la visibilidad)
            // y despues se rellena el dato propio de la reserva
            if (r instanceof Empresa) {
                vista.empresasRB.doClick();
                vista.siCheckBox.setSelected(((Empresa) r).isFactura());
            } else if (r instanceof GrupoAmigos) {
                vista.grupoAmigosRB.doClick();
                vista.nombreEquipoTxt.setText(((GrupoAmigos) r).getNombreEquipo());
            } else {
                vista.cumpleaniosRB.doClick();
                vista.edadTxt.setText(String.valueOf(((Cumpleanios) r).getEdadCumpleaniero()));
            }
        }
    }

    // para cerrar la ventana

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("Deseas cerrar la ventana", "Salir");
        if (resp == JOptionPane.YES_OPTION) {
            try {
                guardarDatosConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    // no se usan
    @Override public void windowOpened(WindowEvent e) {

    }
    @Override public void windowIconified(WindowEvent e) {

    }
    @Override public void windowDeiconified(WindowEvent e) {

    }
    @Override public void windowActivated(WindowEvent e) {

    }
    @Override public void windowDeactivated(WindowEvent e) {

    }
    @Override public void windowClosed(WindowEvent e) {

    }
}
