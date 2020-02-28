/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Dante_Sanchez
 */

//aqui hare mi conexion a mi base de datos
public class Conexion {
    
    public String base ="dante_hotel_completo";
    public String url ="jdbc:mysql://localhost/" + base;
    public String user ="root";
    public String pass ="";
    
    
    //importo la clase Connection de libreria java.mysql
    
    //creamos metodo de tipo Connection
    public Connection conectar() {
        //variable de tipo Connection comienza nula
        Connection cn = null;
        
        try {
            //indicamos donde se encuentra la calse Driver de nuestro jdbc para indicar que lo tome de ahi
            Class.forName("org.gjt.mm.mysql.Driver");
            
            //llenamos nuestra variable y le asignamos los valores de nuestra base
            //deben estar en este orden para la conexion url,user,pass
            // el (Connection) es un casteo
            cn = (Connection)DriverManager.getConnection(this.url,this.user,this.pass);
            
            //probamos la conexion en pantalla
            System.out.println("Conexion a bd: " + base + " exitosa");
            
        } catch (Exception e) {
            
            //mandamos un mensaje en pantalla si no fue correcta nuestra conexion
            JOptionPane.showMessageDialog(null, e);
            
            //mandamos mensaje en consola
            System.out.println("Error al conectar a : " + base);
        }
        
        //el metodo debe regresar un valor 
        return cn;
    }
    
    
    
}
