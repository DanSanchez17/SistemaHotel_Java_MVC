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
//ENCAPSULAMIENTO DE DATOS DE TABLA ADMINISTRADORES
public class Administrador extends Empleado{
    
    //atrivutos de mi tabla administradores
    private int idadministradores;
    private int idempleado;
    private String acceso;
    private String usuario;
    private String password;
    
    //creo mi constructor vacio
    public Administrador(){
        
    }
    
    //creo getters y setters

    public int getIdadministradores() {
        return idadministradores;
    }

    public void setIdadministradores(int idadministradores) {
        this.idadministradores = idadministradores;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
