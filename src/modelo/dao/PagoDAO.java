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
import modelo.Pago;

/**
 *
 * @author Dante_Sanchez
 */
public class PagoDAO extends Conexion{
    
    //para registros de Jtable
    public Integer totalregistros;
    
    //metodos CRUD
    
    public DefaultTableModel mostrar(String buscar) {
        
        //llamamos conexion 
        Connection cn = conectar();
        //preparamos consulta
        PreparedStatement ps = null;
       DefaultTableModel modelo;
       
       String [] titulos = {"ID","IDRESERVA","FOLIO","PAGO_TOTAL","FECHA_PAGO","TIPO_PAGO"};
       
       String [] registro =new String [6];
       
       totalregistros=0;
       modelo = new DefaultTableModel(null,titulos);
       
       String sSQL="SELECT * FROM pago WHERE idreservacion="+ buscar + " order by idpago desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("idpago");
               registro [1]=rs.getString("idreservacion");
               registro [2]=rs.getString("folio");
               registro [3]=rs.getString("pagototal");
               registro [4]=rs.getString("fechapago");
               registro [5]=rs.getString("tipopago");
               
               totalregistros=totalregistros+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
    }
    
    public boolean guardarPago(Pago pago) {
        
                //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "INSERT INTO pago (idreservacion,folio,pagototal,fechapago,tipopago) VALUES (?,?,?,?,?)";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
           ps.setInt(1, pago.getIdreservacion());
           ps.setString(2, pago.getFolio());
           ps.setDouble(3, pago.getPagototal());
           ps.setDate(4, pago.getFechapago());
           ps.setString(5, pago.getTipopago());

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
    
    public boolean modificarPago(Pago pago) {
        
                //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "UPDATE pago SET idreservacion=?,folio=?,pagototal=?,fechapago=?,tipopago=?"+
               " WHERE idpago=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
           ps.setInt(1, pago.getIdreservacion());
           ps.setString(2, pago.getFolio());
           ps.setDouble(3, pago.getPagototal());
           ps.setDate(4, pago.getFechapago());
           ps.setString(5, pago.getTipopago());

           //AGREGO idpago
           ps.setInt(6, pago.getIdpago());
           
           
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
    
    public boolean eliminarPago(Pago pago) {
        
                //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "DELETE FROM pago WHERE idpago=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
             ps.setInt(1, pago.getIdpago());
           
           
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
    
    public boolean buscarPago(Pago pago) {
        
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
    }   */
    
}
