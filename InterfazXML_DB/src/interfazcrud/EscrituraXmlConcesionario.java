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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author cuvil
 */
public class EscrituraXmlConcesionario extends EscrituraXmlCoche {

     public static void escritura() throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException{
        ArrayList <Concesionario> concesionarios = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "documento2", null);
        document.setXmlVersion("1.0");
        
        Concesionario c = new Concesionario("manuela_3", "manuel", 8899000, 19);
        concesionarios.add(c);
        Concesionario c1 = new Concesionario("refael alberti", "angusto", 8899001, 20);
        concesionarios.add(c1);
        Concesionario c2 = new Concesionario("las flores", "manolo", 78899000, 119);
        concesionarios.add(c2);
        Concesionario c3 = new Concesionario("Granada", "martirio", 68899000, 29);
        concesionarios.add(c3);
        Concesionario c4 = new Concesionario("Almeria", "laura", 28899030, 13);
        concesionarios.add(c4);
		
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // definimos el elemento raíz del documento
            Element eRaiz = doc.createElement("Concesionario");
            doc.appendChild(eRaiz);

            // definimos el nodo que contendrá los elementos
            
            for(int i = 0; i < concesionarios.size(); i++){
                Element eConcesionario = doc.createElement("direccion");
                eRaiz.appendChild(eConcesionario);

                Element eNombre = doc.createElement("nombre");
                eNombre.appendChild(doc.createTextNode(concesionarios.get(i).getNombre()));
                eNombre.appendChild(eNombre);

                Element telefono = doc.createElement("telefono");
                telefono.appendChild(doc.createTextNode(String.valueOf(concesionarios.get(i).getTelefono())));
                telefono.appendChild(telefono);

                Element eid = doc.createElement("id");
                eid.appendChild(doc.createTextNode(String.valueOf(concesionarios.get(i).getId())));
                eConcesionario.appendChild(eid);
      
            }

            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Concesionario.xml"));

            transformer.transform(source, result);
            }catch(ParserConfigurationException | TransformerException | DOMException e) {
        }
    }
}
