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
import modelos.Sexos;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class SexosControlador {
    
    
     public static boolean agregar(Sexos sexo){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into sexos (nombre_sexo)" 
                    + "values ('" + sexo.getNombre_sexo() + "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(SexosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
     
     public static boolean modificar(Sexos sexo){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update sexos set nombre_sexo='" + sexo.getNombre_sexo() + "'"
                    + " where id_sexo=" + sexo.getId_sexo();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(SexosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Sexos sexo){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from sexos where id_sexo=" + sexo.getId_sexo();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(SexosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    public static Sexos buscarId(Sexos sexo) {
        if (Conexion.conectar()){
            String sql = "select * from sexos where id_sexo ='"+sexo.getId_sexo()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    sexo.setId_sexo(rs.getInt("id_sexo"));
                    sexo.setNombre_sexo(rs.getString("nombre_sexo"));
                } else {
                    sexo.setId_sexo(0);
                    sexo.setNombre_sexo("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return sexo;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from sexos where upper(nombre_sexo) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_sexo offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_sexo") + "</td>"
                                + "<td>" + rs.getString("nombre_sexo") + "</td>"
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
