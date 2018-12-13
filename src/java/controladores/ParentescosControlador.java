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
import modelos.Parentescos;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class ParentescosControlador {
    
    
     public static boolean agregar(Parentescos parentesco){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into parentescos (nombre_parentesco)" 
                    + "values ('" + parentesco.getNombre_parentesco()+ "')";
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ParentescosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
     
     public static boolean modificar(Parentescos parentesco){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update parentescos set nombre_parentesco='" + parentesco.getNombre_parentesco() + "'"
                    + " where id_parentesco=" + parentesco.getId_parentesco();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ParentescosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    
    public static boolean eliminar(Parentescos parentesco){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from parentescos where id_parentesco=" + parentesco.getId_parentesco();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ParentescosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        
        return valor;
        
    }
    public static Parentescos buscarId(Parentescos parentesco) {
        if (Conexion.conectar()){
            String sql = "select * from parentescos where id_parentesco ='"+parentesco.getId_parentesco()+"'";
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    parentesco.setId_parentesco(rs.getInt("id_parentesco"));
                    parentesco.setNombre_parentesco(rs.getString("nombre_parentesco"));
                } else {
                    parentesco.setId_parentesco(0);
                    parentesco.setNombre_parentesco("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return parentesco;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from parentescos where upper(nombre_parentesco) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_parentesco offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_parentesco") + "</td>"
                                + "<td>" + rs.getString("nombre_parentesco") + "</td>"
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
