/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazcrud;

/**
 *
 * @author cuvil
 */

import java.util.ArrayList;
import javafx.scene.layout.Region;
import javax.xml.stream.XMLStreamException;;

import javax.xml.xquery.XQDataSource; 
import javax.xml.xquery.XQConnection; 
import javax.xml.xquery.XQException; 
import javax.xml.xquery.XQResultSequence; 
import javax.xml.xquery.XQExpression; 
import javax.xml.stream.XMLStreamReader; 
import javax.xml.stream.XMLStreamConstants; 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ExistDB {     
    private static String nomClaseDS = "net.xqj.exist.ExistXQDataSource"; 

    public ExistDB() {
        nomClaseDS = nomClaseDS;
    }
	
    public static XQConnection obtenConexion() throws ClassNotFoundException,
        InstantiationException, IllegalAccessException, XQException{
        XQDataSource xqs= (XQDataSource) Class.forName(nomClaseDS).newInstance();
        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");
        xqs.setProperty("user", "admin");
        xqs.setProperty("password", "");

         return xqs.getConnection();    
    }
    public static void insertarXS(XQConnection c,Coche r) throws XQException{
       String cad = "update insert "
        +  "<coche>" + "<nombre>" + r.getNombre() + "</nombre>"+   "<modelo>" + r.getModelo() + "</modelo>"+   "<identificador>" 
        + r.getIdentificador() + "</identificador>" +   "<ano>" + r.getAno() + "</ano>"+ "</coche>"+ " into doc('/db/coches/CochesXml/coche.xml')/coches";


        try { 
              c = obtenConexion();
            XQExpression xqe = c.createExpression(); 
            xqe.executeCommand(cad); 

        } 
        catch (XQException e) { 
                muestraErrorXQuery(e);
        } 
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) { 
        } 

    }
    public void eliminarCoche(XQConnection c,Coche r){

        String delete = "update delete doc(\"//db/coches/CochesXml/coche.xml\")/coches/coche[id=\"" + r.getNombre() + "\"]";
        try { 
            XQExpression xqe = c.createExpression(); 
            xqe.executeCommand(delete);

            } 
            catch (XQException e) { 
                    muestraErrorXQuery(e);
            } 
            catch (Exception e) { 
            } 
    }
    public String limpiarCadena(String cadena){
        cadena = cadena.replace("\n", ""); 
        cadena = cadena.replace("\t", ""); 
        cadena = cadena.replace(" ", "");
        cadena = cadena.replace("\r", "");

        return cadena;
    }
    /**
     *
     * @param c
     * @param r
     * @throws XQException
     * @throws XMLStreamException
     */
    public  void leerXS(XQConnection c,ArrayList<Coche> r) throws XQException, XMLStreamException{
            
        String cadena = null;
        String cad3 = "doc('/db/coches/CochesXml/coche.xml')/coches"; 
          XQExpression xqe3 = c.createExpression(); 
          XQResultSequence xqrs = xqe3.executeQuery(cad3); 
                   String cadenaFinal = new String();

        while (xqrs.next()) { 
            XMLStreamReader xsr = xqrs.getSequenceAsStream();

            while (xsr.hasNext()) { 


                if (xsr.getEventType() == XMLStreamConstants.CHARACTERS) { 
                    String aux = String.valueOf(xsr.getText());

                    if (!aux.equals("")) { 

                        cadenaFinal += aux + "#";
                    } 
                }
                xsr.next(); 

            } 
        } 

        cadenaFinal = limpiarCadena(cadenaFinal);

        String[]  divido = new String[100];
        for(int i = 0;i < 100;i++)
           divido[i] = "null";

        divido  = cadenaFinal.split("#");

        int cont1 = 0;
        int cont = 2;
        System.out.println("coche añadido");
        for(int i = 0;i < cadenaFinal.length()/33;i++){

            Coche re = new Coche();
            re.setNombre(divido[cont]);
            cont += 2;
            re.setModelo(divido[cont]);
            cont += 2;
            re.setIdentificador(Integer.parseInt(divido[cont]));
            cont += 2;
            re.setAno(Integer.parseInt(divido[cont]));
            cont+=4;
            r.add(re);  
        }
            
    }
    public static void borrarCoche(Coche r)throws XQException, XMLStreamException{
        String delete = "update delete doc(\"//db/coches/CochesXml/coche.xml\")/coches/coche[nombre=\"" + r.getNombre() + "\"]";

        XQConnection c = null; 
        try { 
                    c = obtenConexion(); 

                    XQExpression xqe = c.createExpression(); 
                    xqe.executeCommand(delete);

            } 
            catch (XQException e) { 
                    muestraErrorXQuery(e);
            } 
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) { 
            } 
            finally { 
                    try { 
                            if (c != null) { 
                                    c.close(); 
                            } 
                    } 
                    catch (XQException xe) { 
                    } 
            } 



    }
       
    private static void muestraErrorXQuery(XQException e){
        System.err.println("XQuery Error mensaje: " + e.getMessage());
        System.err.println("XQuery Error causas: " + e.getCause());
        System.err.println("XQuery Error códigos: " + e.getVendorCode());
    }

    public  void principal() { 
            XQConnection c = null; 
            try { 
                    c = obtenConexion(); 
                    String cad = "doc('/db/coches/CochesXml/coche.xml')/coches/coche/ nombre"; 
                    XQExpression xqe = c.createExpression(); 
                    XQResultSequence xqrs = xqe.executeQuery(cad); 
                    int i=1; 
                    while (xqrs.next()) { 
                            System.out.println("[Resultado "+(i++)+"]"); 
                            XMLStreamReader xsr = xqrs.getItemAsStream(); 
                            while (xsr.hasNext()) { 
                                    if (xsr.getEventType() == XMLStreamConstants.CHARACTERS) { 
                                            System.out.println(xsr.getText()); 
                                    } 
                                    xsr.next(); 
                            } 
                    }        
            } 
            catch (XQException e) { 
                    muestraErrorXQuery(e);
            } 
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException | XMLStreamException e) { 
            } 
            finally { 
                    try { 
                            if (c != null) { 
                                    c.close(); 
                            } 
                    } 
                    catch (XQException xe) { 
                    } 
            } 
	} 
}