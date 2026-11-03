package com.shumayac.scaperoom.gui;

import com.shumayac.scaperoom.base.Cumpleanios;
import com.shumayac.scaperoom.base.Empresa;
import com.shumayac.scaperoom.base.GrupoAmigos;
import com.shumayac.scaperoom.base.Reserva;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;
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


    //Añadir alta de grupo de amigos
    public void altaGrupoAmigos(String nombre, String apellido, String telefono, String email,
                                int numJugadores, String rangoEdad, LocalDate fechaReserva,
                                String complejidad, String nombreEquipo) {
        listaReservas.add(new GrupoAmigos(nombre, apellido, telefono, email,
                numJugadores, rangoEdad, fechaReserva, complejidad, nombreEquipo));
    }
    //alta de reserva de empresa
    public void altaEmpresa(String nombre, String apellido, String telefono, String email,
                            int numJugadores, String rangoEdad, LocalDate fechaReserva,
                            String complejidad, boolean factura) {
        listaReservas.add(new Empresa(nombre, apellido, telefono, email,
                numJugadores, rangoEdad, fechaReserva, complejidad, factura));
    }
    //alta de cumpleaños
    public void altaCumpleanios(String nombre, String apellido, String telefono, String email,
                                int numJugadores, String rangoEdad, LocalDate fechaReserva,
                                String complejidad, int edadCumpleaniero) {
        listaReservas.add(new Cumpleanios(nombre, apellido, telefono, email,
                numJugadores, rangoEdad, fechaReserva, complejidad, edadCumpleaniero));
    }

    //para modificar reserva

    public void modificarReserva(Reserva reserva, String nombre, String apellido, String telefono,
                                 String email, int numJugadores, String rangoEdad,
                                 LocalDate fechaReserva, String complejidad,
                                 boolean factura, String nombreEquipo, int edadCumpleaniero) {
        //campos de reserva general
        reserva.setNombre(nombre);
        reserva.setApellido(apellido);
        reserva.setTelefono(telefono);
        reserva.setEmail(email);
        reserva.setNumJugadores(numJugadores);
        reserva.setRangoEdad(rangoEdad);
        reserva.setFechaReserva(fechaReserva);
        reserva.setComplejidad(complejidad);

        // campo diferente para cada tipo
        if (reserva instanceof Empresa) {
            ((Empresa) reserva).setFactura(factura);
        } else if (reserva instanceof GrupoAmigos) {
            ((GrupoAmigos) reserva).setNombreEquipo(nombreEquipo);
        } else if (reserva instanceof Cumpleanios) {
            ((Cumpleanios) reserva).setEdadCumpleaniero(edadCumpleaniero);
        }
    }
    //para eliminar reserva
    public void eliminarReserva(Reserva reserva) {
        listaReservas.remove(reserva);
    }


    //para saber si existe la reserva, comprobacion
    //coincide si es la misma resrva si es el mismo email y la misma fecha
    public boolean existeReserva(String email, LocalDate fechaReserva) {
        for (Reserva unaReserva : listaReservas) {
            if (unaReserva.getEmail().equalsIgnoreCase(email)
                    && unaReserva.getFechaReserva().equals(fechaReserva)) {
                return true;
            }
        }
        return false;
    }


    //exportar el XML
    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        // añado el nodo raiz
        Element raiz = documento.createElement("reservas");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoReserva = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Reserva unaReserva : listaReservas) {
            // dentro de reservas añado una etiqueta con el tipo de reserva
            if (unaReserva instanceof Empresa) {
                nodoReserva = documento.createElement("Empresa");
            } else if (unaReserva instanceof Cumpleanios) {
                nodoReserva = documento.createElement("Cumpleanios");
            } else {
                nodoReserva = documento.createElement("GrupoAmigos");
            }
            raiz.appendChild(nodoReserva);

            // dentro de la etiqueta de la reserva -> nombre, apellido...
            nodoDatos = documento.createElement("nombre");
            nodoReserva.appendChild(nodoDatos);
            texto = documento.createTextNode(unaReserva.getNombre());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("apellido");
            nodoReserva.appendChild(nodoDatos);
            texto = documento.createTextNode(unaReserva.getApellido());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("telefono");
            nodoReserva.appendChild(nodoDatos);
            texto = documento.createTextNode(unaReserva.getTelefono());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("email");
            nodoReserva.appendChild(nodoDatos);
            texto = documento.createTextNode(unaReserva.getEmail());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("numjugadores");
            nodoReserva.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unaReserva.getNumJugadores()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("rangoedad");
            nodoReserva.appendChild(nodoDatos);
            texto = documento.createTextNode(unaReserva.getRangoEdad());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fechareserva");
            nodoReserva.appendChild(nodoDatos);
            texto = documento.createTextNode(unaReserva.getFechaReserva().toString());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("complejidad");
            nodoReserva.appendChild(nodoDatos);
            texto = documento.createTextNode(unaReserva.getComplejidad());
            nodoDatos.appendChild(texto);

            // ponemos el campo diferente de cada tipo
            if (unaReserva instanceof Empresa) {
                nodoDatos = documento.createElement("factura");
                nodoReserva.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Empresa) unaReserva).isFactura()));
                nodoDatos.appendChild(texto);
            } else if (unaReserva instanceof GrupoAmigos) {
                nodoDatos = documento.createElement("nombreequipo");
                nodoReserva.appendChild(nodoDatos);
                texto = documento.createTextNode(((GrupoAmigos) unaReserva).getNombreEquipo());
                nodoDatos.appendChild(texto);
            } else {
                nodoDatos = documento.createElement("edadcumpleaniero");
                nodoReserva.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((Cumpleanios) unaReserva).getEdadCumpleaniero()));
                nodoDatos.appendChild(texto);
            }
        }

        // guardo en fichero
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);
    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        // se lee a una lista temporal: si el fichero falla a medias, no se pierden los datos actuales
        ArrayList<Reserva> listaNueva = new ArrayList<Reserva>();
        Reserva nueva = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoReserva = (Element) listaElementos.item(i);
            String etiqueta = nodoReserva.getTagName();

            nueva = null;
            if (etiqueta.equals("Empresa")) {
                Empresa empresa = new Empresa();
                //valor de lo que esta pulsado en el check box (recuerda que es un booleano)
                empresa.setFactura(Boolean.parseBoolean(
                        nodoReserva.getElementsByTagName("factura").item(0).getTextContent()));
                nueva = empresa;
            } else if (etiqueta.equals("Cumpleanios")) {
                Cumpleanios cumple = new Cumpleanios();
                cumple.setEdadCumpleaniero(Integer.parseInt(
                        nodoReserva.getElementsByTagName("edadcumpleaniero").item(0).getTextContent()));
                nueva = cumple;
            } else if (etiqueta.equals("GrupoAmigos")) {
                GrupoAmigos amigos = new GrupoAmigos();
                amigos.setNombreEquipo(
                        nodoReserva.getElementsByTagName("nombreequipo").item(0).getTextContent());
                nueva = amigos;
            }

            // si la etiqueta era de un tipo de reserva, relleno los campos comunes
            if (nueva != null) {
                nueva.setNombre(nodoReserva.getElementsByTagName("nombre").item(0).getTextContent());
                nueva.setApellido(nodoReserva.getElementsByTagName("apellido").item(0).getTextContent());
                nueva.setTelefono(nodoReserva.getElementsByTagName("telefono").item(0).getTextContent());
                nueva.setEmail(nodoReserva.getElementsByTagName("email").item(0).getTextContent());
                nueva.setNumJugadores(Integer.parseInt(
                        nodoReserva.getElementsByTagName("numjugadores").item(0).getTextContent()));
                nueva.setRangoEdad(nodoReserva.getElementsByTagName("rangoedad").item(0).getTextContent());
                nueva.setFechaReserva(LocalDate.parse(
                        nodoReserva.getElementsByTagName("fechareserva").item(0).getTextContent()));
                nueva.setComplejidad(nodoReserva.getElementsByTagName("complejidad").item(0).getTextContent());
                listaNueva.add(nueva);
            }
        }
        listaReservas = listaNueva;
    }
}