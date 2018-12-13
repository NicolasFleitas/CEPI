/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Nacionalidades;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class NacionalidadesControlador {
    public static boolean agregar(Nacionalidades nacionalidad){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into nacionalidades (nombre_nacionalidad)" 
                    + "values ('" + nacionalidad.getNombre_nacionalidad() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(NacionalidadesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean modificar(Nacionalidades nacionalidad){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update nacionalidades set nombre_nacionalidad='" + nacionalidad.getNombre_nacionalidad() + "'"
                    + " where id_nacionalidad=" + nacionalidad.getId_nacionalidad();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(NacionalidadesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Nacionalidades nacionalidad){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from nacionalidades where id_nacionalidad=" + nacionalidad.getId_nacionalidad();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(NacionalidadesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static Nacionalidades buscarId(Nacionalidades nacionalidad) {
        if (Conexion.conectar()){
            String sql = "select * from nacionalidades where id_nacionalidad ='"+nacionalidad.getId_nacionalidad()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    nacionalidad.setId_nacionalidad(rs.getInt("id_nacionalidad"));
                    nacionalidad.setNombre_nacionalidad(rs.getString("nombre_nacionalidad"));
                } else {
                    nacionalidad.setId_nacionalidad(0);
                    nacionalidad.setNombre_nacionalidad("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return nacionalidad;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from nacionalidades where upper(nombre_nacionalidad) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_nacionalidad offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_nacionalidad") + "</td>"
                                + "<td>" + rs.getString("nombre_nacionalidad") + "</td>"
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
