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
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.Servicio;

/**
 *
 * @author Dante_Sanchez
 */
public class ServicioDAO extends Conexion{
    
    //para registros de Jtable
    public Integer total;
    
    //metodos CRUD
    
    public DefaultTableModel mostrar(String buscar) {
        
        Connection cn = conectar();
        PreparedStatement ps = null;
        DefaultTableModel tabla;
        
        String [] encabezado = {"ID","SERVICIO","DESCRIPCION","PRECIO"};
        String [] registro = new String[4];
        
        total=0;
        tabla = new DefaultTableModel(null,encabezado);
        

        String sql2 ="SELECT idservicio,nombre,descripcion,precioservicio FROM servicio "
                + "WHERE nombre like '%"+ buscar + "%' order by idservicio";
        
        try {
            
            ps = cn.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                registro [0] = rs.getString("idservicio");
                registro [1] = rs.getString("nombre");
                registro [2] = rs.getString("descripcion");
                registro [3] = rs.getString("precioservicio");

                total = total + 1;
                tabla.addRow(registro);
            }
            
            return tabla;
                    
            
        } catch (SQLException e) {
            System.err.println("error en tablaDAO " + e);
            return null;
        }
    }
    
    public boolean guardarServicio(Servicio servicio) {
        
                //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "INSERT INTO servicio (nombre,descripcion,precioservicio) VALUES (?,?,?)";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setDouble(3, servicio.getPrecioservicio());
            
            int consulta = ps.executeUpdate();
            
            if (consulta != 0) {
                
                return true;
                
            }else {
                return false;
            }

            
        } catch (SQLException e) {
            //pinto error
            System.err.println("ERROR EN DAO----- " + e);
            return false;
        }
        
        //cerramos la conexion
        finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
        
    }
    
    public boolean modificarServicio(Servicio servicio) {
        
                //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "UPDATE servicio SET nombre=?,descripcion=?,precioservicio=? WHERE idservicio=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
            ps.setString(1, servicio.getNombre());
            ps.setString(2, servicio.getDescripcion());
            ps.setDouble(3, servicio.getPrecioservicio());
            
            //agrego idservicio
            ps.setInt(4, servicio.getIdservicio());
            
            int consulta = ps.executeUpdate();
            
            if (consulta != 0) {
                return true;
                
            }else {
                return false;
            }
            
        } catch (SQLException e) {
            //pinto error
            System.err.println("ERROR EN DAO----- " + e);
            return false;
        }
        
        //cerramos la conexion
        finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
    }
    
    public boolean eliminarServicio(Servicio servicio) {
        
                //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "DELETE FROM servicio WHERE idservicio=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
            //agrego idservicio
            ps.setInt(1, servicio.getIdservicio());
            
            int consulta = ps.executeUpdate();
            
            if (consulta != 0) {
                return true;
                
            }else {
                return false;
            }
            
            
        } catch (SQLException e) {
            //pinto error
            System.err.println("ERROR EN DAO----- " + e);
            return false;
        }
        
        //cerramos la conexion
        finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        
    }
    
    
    
    /*
    public boolean buscarServicio(Servicio servicio) {
        
                //iniciamos la conexion
        Connection cn = conectar();
        
        //preparo consulta como sera solo una creare un inner join de dos tablas
        PreparedStatement ps = null;
        
        //Preparo un ResultSEt que me regreaa informarcion alojada en mi bd
        ResultSet rs =null;
        
        //almacenamos la consulta en un String
        String sql="";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
        } catch (SQLException e) {
            //pinto error
            System.err.println("error en buscarDAO admin " + e);
            //regreso false si hay error
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
    } */
    
}
