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
import modelo.Consumo;

/**
 *
 * @author Dante_Sanchez
 */
public class ConsumoDAO extends Conexion{
    
    //para registros de Jtable
    public Integer totalregistros;
    public Double totalconsumo;
    
    //metodos CRUD
    
    public DefaultTableModel mostrar(String buscar) {
        
                //llamamos conexion 
        Connection cn = conectar();
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","IDRESERVA","IDSERVICIO","SERVICIO","CANTIDAD","PRECIO VENTA"};
       
       String [] registro =new String [6];
       
       totalregistros=0;
       totalconsumo=0.0;
       modelo = new DefaultTableModel(null,titulos);
       
       String sSQL="SELECT c.idconsumo,c.idreservacion,"
               + "c.idservicio,s.nombre,c.cantidad,c.precioventa "
               + " FROM consumo c INNER JOIN servicio s on c.idservicio=s.idservicio"
               + " WHERE c.idreservacion="+ buscar + " order by c.idconsumo desc";
     /*  
       sSQL="select c.idconsumo,c.idreserva,c.idproducto,p.nombre,c.cantidad,c.precio_venta "
               + ",c.estado from consumo c inner join producto p on c.idproducto=p.idproducto"
               + " where c.idreserva ="+ buscar + " order by c.idconsumo desc";
       */
     
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("idconsumo");
               registro [1]=rs.getString("idreservacion");
               registro [2]=rs.getString("idservicio");
               registro [3]=rs.getString("nombre");
               registro [4]=rs.getString("cantidad");
               registro [5]=rs.getString("precioventa");

               
               totalregistros=totalregistros+1;
               totalconsumo=totalconsumo + (rs.getDouble("cantidad") * rs.getDouble("precioventa") );
               
               modelo.addRow(registro);
               
           }
           System.out.println("ya entro a tabla ");
           return modelo;
           
       } catch (Exception e) {
           System.err.println("Error en tabla DAO CONSUMO---- " + e);
           return null;
       }
    }
    
    public boolean guardarConsumo(Consumo consumo) {
        
        //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "INSERT INTO consumo (idreservacion,idservicio,cantidad,precioventa)" +
               "VALUES (?,?,?,?)";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
           ps.setInt(1, consumo.getIdreservacion());
           ps.setInt(2, consumo.getIdservicio());
           ps.setDouble(3, consumo.getCantidad());
           ps.setDouble(4, consumo.getPrecioventa());

           int n=ps.executeUpdate();
           
           if (n!=0){
               return true;
           }
           else {
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
    
    public boolean modificarConsumo(Consumo consumo) {
        
                //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "UPDATE consumo SET idreservacion=?,idservicio=?,cantidad=?,precioventa=?"+
               " WHERE idconsumo=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
           ps.setInt(1, consumo.getIdreservacion());
           ps.setInt(2, consumo.getIdservicio());
           ps.setDouble(3, consumo.getCantidad());
           ps.setDouble(4, consumo.getPrecioventa());
           
           //Agrego idconsumo
           ps.setInt(5, consumo.getIdconsumo());
           
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
    
    public boolean eliminarConsumo(Consumo consumo) {
        
                //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "DELETE FROM consumo WHERE idconsumo=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
             ps.setInt(1, consumo.getIdconsumo());
           
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
    public boolean buscarConsumo(Consumo consumo) {
        
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
    }  */
    
}