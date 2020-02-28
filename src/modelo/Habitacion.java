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
//ENCAPSULAMIENTO DE DATOS DE TABLA HABITACION
public class Habitacion {
    
    //atributos de tabla habitacion
    private int idhabitacion;
    private int numero;
    private String piso; //lo pongo string para ocuparlo en el controlador
    private String tipocama;
    private String categoria;
    private String caracteristicas;
    private double preciodia;
    private String estado;
    
    //constructor vacio
    public Habitacion() {
        
    }
    
    //getters y setters

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getTipocama() {
        return tipocama;
    }

    public void setTipocama(String tipocama) {
        this.tipocama = tipocama;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public double getPreciodia() {
        return preciodia;
    }

    public void setPreciodia(double preciodia) {
        this.preciodia = preciodia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
