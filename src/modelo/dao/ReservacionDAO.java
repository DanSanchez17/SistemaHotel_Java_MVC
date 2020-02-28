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
import modelo.Reservacion;

/**
 *
 * @author Dante_Sanchez
 */
public class ReservacionDAO extends Conexion{
    
    //para registros de Jtable
    public Integer total;
    
    //metodos CRUD
    
    public DefaultTableModel mostrar(String buscar) {
        
       DefaultTableModel modelo;
       Connection cn = conectar();
       String [] titulos = {"ID","Idhabitacion","HABITACION","idcliente","CLIENTE","idempleado","ATENDIO :","RESERVA","F.INGRESO","F.SALIDA","COSTO","ESTADO"};
       
       String [] registro =new String [12];
       
       total=0;
       modelo = new DefaultTableModel(null,titulos);
       
       String sSQL="SELECT r.idreservacion,r.idhabitacion,h.numero,r.idcliente,"+
               "(SELECT nombre FROM persona WHERE idpersona=r.idcliente)as clienten,"+
               "(SELECT paterno FROM persona WHERE idpersona=r.idcliente)as clienteap,"+
               "r.idempleado,(SELECT nombre FROM persona WHERE idpersona=r.idempleado)as trabajadorn,"+
               "(SELECT paterno FROM persona where idpersona=r.idempleado)as trabajadorap,"+
               "r.codigoreserva,r.fechaingreso,r.fec"
               + "hasalida,"+
               "r.precioreserva,r.estado from reservacion r INNER JOIN habitacion h on r.idhabitacion=h.idhabitacion where r.fechaingreso like '%"+ buscar + "%' order by idreservacion desc";
       
       try {
           Statement st= cn.createStatement();
           ResultSet rs=st.executeQuery(sSQL);
           
           while(rs.next()){
               registro [0]=rs.getString("idreservacion");
               registro [1]=rs.getString("idhabitacion");
               registro [2]=rs.getString("numero");
               registro [3]=rs.getString("idcliente");
               registro [4]=rs.getString("clienten") + " " + rs.getString("clienteap") ;
               registro [5]=rs.getString("idempleado");
               registro [6]=rs.getString("trabajadorn") + " " + rs.getString("trabajadorap");
               registro [7]=rs.getString("codigoreserva");
               registro [8]=rs.getString("fechaingreso");
               registro [9]=rs.getString("fechasalida");
               registro [10]=rs.getString("precioreserva");
               registro [11]=rs.getString("estado");
               
               
               total=total+1;
               modelo.addRow(registro);
               
           }
           return modelo;
           
       } catch (Exception e) {
           JOptionPane.showConfirmDialog(null, e);
           return null;
       }
    }
    
    public boolean guardarReservacion(Reservacion reservacion) {
        
                //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "INSERT INTO reservacion (idhabitacion,idcliente,idempleado,codigoreserva,fechaingreso,fechasalida,"
                + "precioreserva,estado) VALUES (?,?,?,?,?,?,?,?)";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
           ps.setInt(1, reservacion.getIdhabitacion());
           ps.setInt(2, reservacion.getIdcliente());
           ps.setInt(3, reservacion.getIdempleado());
           ps.setString(4, reservacion.getCodigoreserva());
           ps.setDate(5, reservacion.getFechaingreso());
           ps.setDate(6, reservacion.getFechasalida());
           ps.setDouble(7, reservacion.getPrecioreserva());
           ps.setString(8, reservacion.getEstado());
           
           
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
    
    public boolean modificarReservacion(Reservacion reservacion) {
        
                //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "UPDATE reservacion SET idhabitacion=?,idcliente=?,idempleado=?,codigoreserva=?,fechaingreso=?,fechasalida=?,"
                + "precioreserva=?,estado=? WHERE idreservacion=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
           ps.setInt(1, reservacion.getIdhabitacion());
           ps.setInt(2, reservacion.getIdcliente());
           ps.setInt(3, reservacion.getIdempleado());
           ps.setString(4, reservacion.getCodigoreserva());
           ps.setDate(5, reservacion.getFechaingreso());
           ps.setDate(6, reservacion.getFechasalida());
           ps.setDouble(7, reservacion.getPrecioreserva());
           ps.setString(8, reservacion.getEstado());
           
           //agrego idreservacion
           ps.setInt(9, reservacion.getIdreservacion());
           
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
    
    public boolean eliminarReservacion(Reservacion reservacion) {
        
                //llamo a conexion
        Connection cn = conectar();
        
        //prearamos consulta
        PreparedStatement ps = null;
        
        //almacenamos consulta sql
        String sql = "DELETE FROM reservacion WHERE idreservacion=?";
        
        try {
            
            ps = cn.prepareStatement(sql);
            
            ps.setInt(1, reservacion.getIdreservacion());
           
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
    public boolean buscarReservacion(Reservacion reservacion) {
        
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
