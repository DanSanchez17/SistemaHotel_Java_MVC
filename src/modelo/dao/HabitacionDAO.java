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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.Habitacion;

/**
 *
 * @author Dante_Sanchez
 */
//MANEJO DE DATOS DE TABLA HABITACION
public class HabitacionDAO extends Conexion{
    
    //para registros de Jtable
    public Integer total;
    
    //metodos CRUD
    
    public DefaultTableModel mostrar(String buscar) {
        
        Connection cn = conectar();
        PreparedStatement ps = null;
        DefaultTableModel tabla;
        
        String [] encabezado = {"ID","NUMERO","PISO","TIPOCAMA","CATEGORIA","CARACTERISTICAS","PRECIO","ESTADO"};
        String [] registro = new String[8];
        
        total=0;
        tabla = new DefaultTableModel(null,encabezado);
        

        String sql2 ="SELECT idhabitacion,numero,piso,tipocama,categoria,caracteristicas,preciodia,estado FROM habitacion "
                + "WHERE piso like '%"+ buscar + "%' order by idhabitacion";
        
        try {
            
            ps = cn.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                registro [0] = rs.getString("idhabitacion");
                registro [1] = rs.getString("numero");
                registro [2] = rs.getString("piso");
                registro [3] = rs.getString("tipocama");
                registro [4] = rs.getString("categoria");
                registro [5] = rs.getString("caracteristicas");
                registro [6] = rs.getString("preciodia");
                registro [7] = rs.getString("estado");
                
                total = total + 1;
                tabla.addRow(registro);
            }
            
            return tabla;
                    
            
        } catch (SQLException e) {
            System.err.println("error en tablaDAO " + e);
            return null;
        }
    }
    
    
       public DefaultTableModel mostrarvista(String buscar){
       DefaultTableModel modelo;
       Connection cn = conectar();
       
       String [] titulos = {"ID","NUMERO","PISO","TIPOCAMA","CATEGORIA","CARACTERISTICAS","PRECIO","ESTADO"};
       
       String [] registro =new String [8];
       
       total=0;
       modelo = new DefaultTableModel(null,titulos);
       
       String sSQL="select * from habitacion where piso like '%"+ buscar + "%' and estado='Disponible' order by idhabitacion";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0] = rs.getString("idhabitacion");
                registro [1] = rs.getString("numero");
                registro [2] = rs.getString("piso");
                registro [3] = rs.getString("tipocama");
                registro [4] = rs.getString("categoria");
                registro [5] = rs.getString("caracteristicas");
                registro [6] = rs.getString("preciodia");
                registro [7] = rs.getString("estado");
               
               total=total+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
     } 
       
       
       
       
    
    public boolean guardarHabitacion(Habitacion habitacion) {
        //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "INSERT INTO habitacion (numero,piso,tipocama,categoria,caracteristicas,preciodia,estado) VALUES (?,?,?,?,?,?,?)";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
            ps.setInt(1, habitacion.getNumero());
            ps.setString(2, habitacion.getPiso());
            ps.setString(3, habitacion.getTipocama());
            ps.setString(4, habitacion.getCategoria());
            ps.setString(5, habitacion.getCaracteristicas());
            ps.setDouble(6, habitacion.getPreciodia());
            ps.setString(7, habitacion.getEstado());
            
             int consulta = ps.executeUpdate();
            
            if (consulta != 0) {
                
                return true;
                
            }else {
                System.out.println("no entro");
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
    
    
    //metodo modificar
    public boolean modificarHabitacion(Habitacion habitacion) {
                //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "UPDATE habitacion SET numero=?,piso=?,tipocama=?,categoria=?,caracteristicas=?,"
                + "preciodia=?,estado=? WHERE idhabitacion=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
            ps.setInt(1, habitacion.getNumero());
            ps.setString(2, habitacion.getPiso());
            ps.setString(3, habitacion.getTipocama());
            ps.setString(4, habitacion.getCategoria());
            ps.setString(5, habitacion.getCaracteristicas());
            ps.setDouble(6, habitacion.getPreciodia());
            ps.setString(7, habitacion.getEstado());
            
            //agrego el idhabitacion
            ps.setInt(8, habitacion.getIdhabitacion());
            
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
    
    public boolean eliminarHabitacion(Habitacion habitacion) {
        
        //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "DELETE FROM habitacion WHERE idhabitacion=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
            ps.setInt(1, habitacion.getIdhabitacion());
            
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
    public boolean buscarHabitacion(Habitacion habitacion) {
        
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
