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
//ENCAPSULAMIENTO DE DATOS DE TABLA PAGO
public class Pago {
    
    private int idpago;
    private int idreservacion;
    private String folio;
    private double pagototal;
    private Date fechapago;
    private String tipopago;

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }
    
    
    //constructor vacio
    public Pago() {
        
    }
    
    //getters y setters

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public int getIdreservacion() {
        return idreservacion;
    }

    public void setIdreservacion(int idreservacion) {
        this.idreservacion = idreservacion;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public double getPagototal() {
        return pagototal;
    }

    public void setPagototal(double pagototal) {
        this.pagototal = pagototal;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }
    
    
}
