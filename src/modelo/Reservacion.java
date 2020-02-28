/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author Dante_Sanchez
 */
//ENCAPSULAMIENTO DE DATOS TABLA RESERVACION
public class Reservacion {
    
    private int idreservacion;
    private int idhabitacion;
    private int idcliente;
    private int idempleado;
    private Date fechaingreso;
    private Date fechasalida;
    private double precioreserva;
    private String estado;
    private String codigoreserva;

    public String getCodigoreserva() {
        return codigoreserva;
    }

    public void setCodigoreserva(String codigoreserva) {
        this.codigoreserva = codigoreserva;
    }
    
    //constructor vacio
    public Reservacion() {
        
    }
    
    
    //getters y setters

    public int getIdreservacion() {
        return idreservacion;
    }

    public void setIdreservacion(int idreservacion) {
        this.idreservacion = idreservacion;
    }

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public double getPrecioreserva() {
        return precioreserva;
    }

    public void setPrecioreserva(double precioreserva) {
        this.precioreserva = precioreserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
