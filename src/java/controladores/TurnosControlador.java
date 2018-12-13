/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author nicof
 */
import modelos.Turnos;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class TurnosControlador {
    
    
     public static boolean agregar(Turnos turno){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into turnos (nombre_turno)" 
                    + "values ('" + turno.getNombre_turno() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TurnosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
     
     public static boolean modificar(Turnos turno){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update turnos set nombre_turno='" + turno.getNombre_turno() + "'"
                    + " where id_turno=" + turno.getId_turno();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TurnosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Turnos turno){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from turnos where id_turno=" + turno.getId_turno();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(TurnosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    public static Turnos buscarId(Turnos turno) {
        if (Conexion.conectar()){
            String sql = "select * from turnos where id_turno ='"+turno.getId_turno()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    turno.setId_turno(rs.getInt("id_turno"));
                    turno.setNombre_turno(rs.getString("nombre_turno"));
                } else {
                    turno.setId_turno(0);
                    turno.setNombre_turno("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return turno;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from turnos where upper(nombre_turno) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_turno offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_turno") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    
    
    
    
    
}
