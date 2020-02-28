/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Conexion;

/**
 *
 * @author Dante_Sanchez
 */

//MANEJO DE DATOS DE TABLA CLIENTE(DAO)

//heredo de clase conexion 
public class ClienteDAO extends Conexion{
    
    public Integer total;
    //creo metodos 
    
    //metodo guardarCliente
    public boolean guardarCliente(Cliente cliente) {
        
        //llamos Connection 
        Connection cn = conectar();
        
        //preparo consulta PreparedStatement, lo comienzo nulo
        PreparedStatement ps = null;
        //Preparamos otra consulta, ya que se ocupan 2 consultas en este DAO
        PreparedStatement ps2 = null;
        
        //almaceno mi consulta
        //como cliente es una tabla heredada de persona haremos dos consultas
        String sql ="INSERT INTO persona(nombre,paterno,materno,telefono,correo) VALUES (?,?,?,?,?)";
        String sql2 = "INSERT INTO cliente(idpersona,codigocliente) VALUES ((SELECT idpersona FROM persona ORDER BY idpersona DESC LIMIT 1),?)";
        
        //metemos intrucciones en un try-catch
        try {
            ps = cn.prepareStatement(sql);
            ps2 = cn.prepareStatement(sql2);
            
            //primero tomo los atributos de primer consulta
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getPaterno());
            ps.setString(3, cliente.getMaterno());
            ps.setInt(4, cliente.getTelefono());
            ps.setString(5, cliente.getCorreo());
            
            //ahora tomo los atributos de mi segunda consulta
            ps2.setString(1, cliente.getCodigocliente());
            
            //creo una variable que ejecute la consulta y alamcene para una validacion
            int consulta1 = ps.executeUpdate();
            
            //SI la consulta devuelve un valor didferente de 0 quiere decir que si puede continuar en el registro a bd 
            if(consulta1 != 0){
                
                int consulta2 = ps2.executeUpdate();
                
                //SI consulta2 es diferente de 0  realiza el registro completo  y retorna un true (Todo salio bien)
                if(consulta2 != 0) {
                    return true;
                } else {
                    //SINO retornara false(No salio correctamente)
                    return false;
                }
                
            } else {
                
                //SINO cumple desde el principio retornara un false
                return false;
            }
            
            
        } catch (SQLException e) {
            System.err.println("hubo un error en insercion de datos: " +  e);
            return false;
        }
        
        //cierro conexion
        finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
                
        }
        
        
    }
    
    //metodo modificarCliente
    public boolean modificarCliente(Cliente cliente) {
        
        Connection cn = conectar();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        
        String sql ="UPDATE persona SET nombre=?,paterno=?,materno=?,telefono=?,correo=? WHERE idpersona=?";
        String sql2 = "UPDATE cliente SET codigocliente=? WHERE idpersona=? ";
        
        try {
            
            ps = cn.prepareStatement(sql);
            ps2 = cn.prepareStatement(sql2);
            
            //para primer consulta
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getPaterno());
            ps.setString(3, cliente.getMaterno());
            ps.setInt(4, cliente.getTelefono());
            ps.setString(5, cliente.getCorreo());
            ps.setInt(6, cliente.getIdpersona());
            
            //para segunda consulta
            ps2.setString(1, cliente.getCodigocliente());
            ps2.setInt(2, cliente.getIdpersona());
            
            int consulta1 = ps.executeUpdate();
            
            if(consulta1 != 0) {
                int consulta2 = ps2.executeUpdate();
                
                if (consulta2 != 0) {
                    return true;
                }else {
                    return false;
                }
            }else{
                return false;
            }
                
            
            
            
        } catch (SQLException e) {
            //cacho el error
            System.err.print( "aqui hay error" + e);
            return false;
        }
        
        //cierro conexion
        finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
                
            }
        }
        
    }
    
    //metodo EliminarCliente
    public boolean eliminarCliente(Cliente cliente) {
        
        Connection cn = conectar();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        
        String sql = "DELETE FROM persona WHERE idpersona=?";
        String sql2 = "DELETE FROM cliente WHERE idpersona=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            ps2 = cn.prepareStatement(sql2);
            
            ps.setInt(1, cliente.getIdpersona());
            
            ps2.setInt(1, cliente.getIdpersona());
            
            int consulta1 = ps.executeUpdate();
            
            if (consulta1 != 0) {
                int consulta2 = ps2.executeUpdate();
                
                    if (consulta2 != 0) {
                    
                        return true;
                }else {
                return false;
            }
                
            } else {
                return false;
            }
                    
                    
        } catch (SQLException e) {
            //cacho el error
            System.err.println(e);
            
            return false;
        }
        
        finally {
           
            try {
                cn.close();
                
            } catch (SQLException e) {
                
                System.err.println(e);
                
            }
           
        }
        
    }
    
    //metodo buscarCliente
    public boolean buscarCliente(Cliente cliente) {
        Connection cn = conectar();
        PreparedStatement ps = null;
        //PreparedStatement ps2 = null;
        
        //Resultset para que devuelva valos ya registrados de la bd
        ResultSet rs = null;
        
        String sql = "SELECT p.idpersona,p.nombre,p.paterno,p.materno,p.telefono,p.correo,"
                    + "c.codigocliente FROM persona p INNER JOIN cliente c ON p.idpersona=c.idpersona WHERE "
                + "codigocliente=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
            ps.setString(1, cliente.getCodigocliente());
            
            //aqui ocupo el ResultSet para mostrar datos almacenados previamente en bd
            rs = ps.executeQuery();
            
            
            //si cumple la condicion retornara un true
            if(rs.next()) {
                
                cliente.setIdpersona(Integer.parseInt(rs.getString("idpersona")));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setPaterno(rs.getString("paterno"));
                cliente.setMaterno(rs.getString("materno"));
                cliente.setTelefono(Integer.parseInt(rs.getString("telefono")));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setCodigocliente(rs.getString("codigocliente"));
                
                return true;
            }
            
            //si no cumple retornara false
            return false;
            
        } catch (SQLException e) {
            
            System.err.println(e);
            return false;
        }
        //cerramos conexion
        finally {
            try {
                cn.close();
                
            } catch (Exception e) {
                
                System.err.println(e);
            }
        }
        
    }
    
    
  
    
    
    public DefaultTableModel mostrar(String buscar) {
        
        Connection cn = conectar();
        
        DefaultTableModel modelo;
        String[] titulos = {"ID","CODIGO_CLIENTE","NOMBRE","PATERNO","MATERNO","TELEFONO","CORREO"};
        String[] registro = new String[7];
        total=0;
        
        modelo = new DefaultTableModel(null,titulos);
        
        String sql ="SELECT p.idpersona,c.codigocliente,p.nombre,p.paterno,p.materno,p.telefono,p.correo FROM persona p INNER JOIN cliente c"
                + " on p.idpersona=c.idpersona WHERE p.nombre like  '%"
                + buscar + "%' order  by idpersona desc ";

                try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("codigocliente");
                registro[2] = rs.getString("nombre");
                registro[3] = rs.getString("paterno");
                registro[4] = rs.getString("materno");
                registro[5] = rs.getString("telefono");
                registro[6] = rs.getString("correo");


                total = total + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
       
    }
}
