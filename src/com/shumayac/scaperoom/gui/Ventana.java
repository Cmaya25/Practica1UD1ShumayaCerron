package com.shumayac.scaperoom.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.shumayac.scaperoom.base.Reserva;

import javax.swing.*;

public class Ventana {

    private JPanel panel1;
    public JRadioButton grupoAmigosRB;
    public JRadioButton empresasRB;
    public JRadioButton cumpleaniosRB;
    public JTextField nombreTxt;
    public JTextField apellidoTxt;
    public JTextField telefonoTxt;
    public JTextField emailTxt;
    public JSpinner spinner2;
    public JComboBox comboBox1;
    public JComboBox complejidadCb;
    public JCheckBox siCheckBox;
    public JLabel facturaLbl;
    public JTextField edadTxt;
    public JLabel edadLbl;
    public JTextField nombreEquipoTxt;
    public JLabel nombreEquipoLbl;
    public DatePicker datePicker;
    public JButton nuevoButton;
    public JButton actualizarButton;
    public JButton eliminarButton;
    public JButton importarButton;
    public JButton exportarButton;
    public JList list1;

    public JFrame frame;
    public DefaultListModel<Reserva> dlmReserva;

    public Ventana() {
        frame = new JFrame("Reserva Scape Room");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        initComponents();
        frame.pack();
        frame.setVisible(true);
    }

    public void initComponents() {
        // modelo de la lista
        dlmReserva = new DefaultListModel<Reserva>();
        list1.setModel(dlmReserva);

        // los tres radios en un grupo para que solo se pueda elegir uno
        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(grupoAmigosRB);
        grupoTipo.add(empresasRB);
        grupoTipo.add(cumpleaniosRB);
        grupoAmigosRB.setSelected(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        spinner2 = new JSpinner(new SpinnerNumberModel(3, 3, 10, 1));
    }
}
