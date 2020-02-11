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
public class EscrituraXML_personal {
        public static void escritura(ArrayList <Personal> personales) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException{
        //ArrayList <Personal> personales = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "documento4", null);
        document.setXmlVersion("1.0");
        
       /* Personal c = new Personal( "antonio", "manco3","informatica", 66);
        personales.add(c);
        Personal c1 = new Personal( "tony", "oscuro","ventas", 166);
        personales.add(c1);
        Personal c2 = new Personal( "antonia", "clara","oficina", 266);
        personales.add(c2);
		*/
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // definimos el elemento raíz del documento
            Element eRaiz = doc.createElement("Personal");
            doc.appendChild(eRaiz);

            // definimos el nodo que contendrá los elementos
            
            for(int i = 0; i < personales.size(); i++){
                Element eid = doc.createElement("id");
                eRaiz.appendChild(eid);

                Element enombre = doc.createElement("nombre");
                enombre.appendChild(doc.createTextNode(personales.get(i).getNombre()));
                eid.appendChild(enombre);

                Element eapellido = doc.createElement("apellido");
                eapellido.appendChild(doc.createTextNode(String.valueOf(personales.get(i).getApellido())));
                eid.appendChild(eapellido);

                Element etel = doc.createElement("id");
                etel.appendChild(doc.createTextNode(String.valueOf(personales.get(i).getId())));
                eid.appendChild(etel);
      
            }

            // clases necesarias finalizar la creación del archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Personal2.xml"));

            transformer.transform(source, result);
            }catch(ParserConfigurationException | TransformerException | DOMException e) {
        }
    }  
}
