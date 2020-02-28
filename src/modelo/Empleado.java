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

//ENCAPSULAMIENTO DE DATOS DE TABLA EMPLEADO
//a la vez heredamos de la clase Persona para poder acceder a sus atributos 
public class Empleado extends Persona{
    
    private String codigoempleado;
    private String puesto;
    private double sueldo;
    
    //creo mi cosntructor vacio de la clase
    public Empleado() {
        
    }
    
    
    //genero mis getters y setters de mis variables

    public String getCodigoempleado() {
        return codigoempleado;
    }

    public void setCodigoempleado(String codigoempleado) {
        this.codigoempleado = codigoempleado;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    
}
