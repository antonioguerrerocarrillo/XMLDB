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
public class LeerXML_personal {
     public static void leer(ArrayList<Personal> arraypersonal) {
        try {
         //   System.out.println("leeyendo el leer xmlpersonal");
            File archivo = new File("C:\\Users\\cuvil\\Documents\\NetBeansProjects\\InterfazCRUD\\ClientesXML.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            Personal c = new Personal();
            document.getDocumentElement().normalize();
            System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());
            NodeList personales = document.getElementsByTagName("personalXML");

            for (int i = 0; i < personales.getLength(); i++) {
                Node nodo = personales.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    c.setNombre(element.getElementsByTagName("nombre").item(0).getTextContent());
                    c.setApellido(element.getElementsByTagName("apellido").item(0).getTextContent());
                    c.setDepartamento(element.getElementsByTagName("departamento").item(0).getTextContent());
                    c.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
                    System.out.println(c.getNombre());
                    System.out.println(c.getApellido());
                    System.out.println(c.getId());
                    System.out.println(c.getDepartamento());
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
        }
    } 
}
