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
//ENCAPSULAMIENTO DE DATOS TABLA SERVICIO
public class Servicio {
    
    private int idservicio;
    private String nombre;
    private String descripcion;
    private double precioservicio;
    
    public Servicio() {
        
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioservicio() {
        return precioservicio;
    }

    public void setPrecioservicio(double precioservicio) {
        this.precioservicio = precioservicio;
    }
    
    
}
