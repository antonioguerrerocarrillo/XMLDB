package interfazcrud;


import interfazcrud.Coche;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cuvil
 */
public class LeerXML_clientes {
     public static void leer(ArrayList<Clientes> arrayclientes) {
        try {
           // System.out.println("leeyendo el leer xmlclientes");
            File archivo = new File("C:\\Users\\cuvil\\Documents\\NetBeansProjects\\InterfazCRUD\\ClientesXML.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            Clientes c = new Clientes();
            document.getDocumentElement().normalize();
            //System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
            NodeList clientes = document.getElementsByTagName("clientesXML");

            for (int i = 0; i < clientes.getLength(); i++) {
                Node nodo = clientes.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    c.setDni(element.getElementsByTagName("dni").item(0).getTextContent());
                    c.setNombre(element.getElementsByTagName("nombre").item(0).getTextContent());
                    c.setApellido(element.getElementsByTagName("apellido").item(0).getTextContent());
                    c.setTelefono(Integer.parseInt(element.getElementsByTagName("telefono").item(0).getTextContent()));
                   // System.out.println(c.getNombre());
                    //System.out.println(c.getApellido());
                    //System.out.println(c.getDni());
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
        }
    }
}
