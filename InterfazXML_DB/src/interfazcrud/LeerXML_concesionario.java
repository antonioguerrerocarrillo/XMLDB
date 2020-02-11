/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazcrud;

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

/**
 *
 * @author cuvil
 */
public class LeerXML_concesionario {
     public static void leer(ArrayList<Concesionario> arrayconcesionarios) {
        try {
           // System.out.println("leeyendo el leer xml de concesionario ");
            File archivo = new File("C:\\Users\\cuvil\\Documents\\NetBeansProjects\\InterfazCRUD\\ConcesionarioXML.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            Concesionario c = new Concesionario();
            document.getDocumentElement().normalize();
            System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
            NodeList concesionarios = document.getElementsByTagName("concesionarioXML");

            for (int i = 0; i < concesionarios.getLength(); i++) {
                Node nodo = concesionarios.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    c.setDireccion(element.getElementsByTagName("direccion").item(0).getTextContent());
                    c.setNombre(element.getElementsByTagName("nombre").item(0).getTextContent());
                    c.setTelefono(Integer.parseInt(element.getElementsByTagName("telefono").item(0).getTextContent()));
                    c.setId((Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent())));
                    System.out.println(c.getNombre());
                    System.out.println(c.getDireccion());
                    System.out.println(c.getTelefono());
                    System.out.println(c.getId());
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
        }
    }
}
