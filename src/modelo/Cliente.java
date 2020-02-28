/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Dante_Sanchez
 */

//ENCAPSULAMIENTO DE DATOS DE TABLA CLIENTE

//heredo de clase persona para jalar los atributos de esa clase
public class Cliente extends Persona{
    
    //decalro atributos de mi tabla cliente
    private String codigocliente;
    
    //creo un  constructor
    public Cliente(){
        
    }
    
    
//genero getters y setters
    
    public String getCodigocliente() {
        return codigocliente;
    }

    public void setCodigocliente(String codigocliente) {
        this.codigocliente = codigocliente;
    }
    
    
    
    
}
