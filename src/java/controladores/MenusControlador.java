package controladores;

import modelos.Menus;
import utiles.Conexion;
import utiles.Utiles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MenusControlador {

    public static Menus buscarId(int id  ) {
        Menus menus = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from menus where id_menu=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        menus = new Menus();
                        menus.setId_menu(rs.getInt("id_menu"));
                        menus.setNombre_menu(rs.getString("nombre_menu"));
                        menus.setCodigo_menu(rs.getString("codigo_menu"));
                    } 
                        
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return menus;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset=(pagina-1)*Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from menus where upper(nombre_menu) like '%" + 
                        nombre.toUpperCase() + 
                        "%' "+
                        "order by id_menu "+
                        "offset "+ offset + " limit "+ Utiles.REGISTROS_PAGINA;
                //System.out.println("--> "+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getString("id_menu") + "</td>"
                               + "<td>" + rs.getString("nombre_menu") + "</td>"
                               + "<td><input type='text' value='" + rs.getString("codigo_menu") + "' size=150 readonly='readonly'></td>"
                               + "</tr>";
                    }
                    if(tabla.equals("")){
                        tabla = "<tr><td  colspan=3>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(Menus menu) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into menus "
                    + "(nombre_menu,codigo_menu) "
                    + "values (?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setString(1, menu.getNombre_menu());
                ps.setString(2, menu.getCodigo_menu());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean modificar(Menus menu) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update menus set nombre_menu=?,"
                    + "codigo_menu=? "
                    + "where id_menu=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setString(1, menu.getNombre_menu());
                ps.setString(2, menu.getCodigo_menu());
                ps.setInt(3, menu.getId_menu());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Menus menu) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from menus where id_menu=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, menu.getId_menu());
                ps.executeUpdate();
                ps.close();
               Conexion.getConn().setAutoCommit(false);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
