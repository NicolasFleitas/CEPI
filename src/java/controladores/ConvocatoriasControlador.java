/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelos.Convocatorias;
import modelos.Cursos;
import utiles.Conexion;
import utiles.Utiles;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelos.Turnos;



public class ConvocatoriasControlador {
    
     private static ArrayList<Convocatorias> listaConvocatoria;
    
      public ArrayList listarConvocatoria() {
        listaConvocatoria = new ArrayList();
        Convocatorias convocatoria = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias cv, cursos c, turnos t"
                        + " where "
                        + "cv.id_curso = c.id_curso"
                        + " and "
                        + "cv.id_turno = t.id_turno"
                        + " and "
                        + " convocatoria_estado like 'HABILITADO'";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    //ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        convocatoria = new Convocatorias();
                        
                        Cursos curso = new Cursos();
                        curso.setNombre_curso(rs.getString("nombre_curso"));
                        
                        Turnos turno = new Turnos();
                        turno.setNombre_turno(rs.getString("nombre_turno"));
                        
                        convocatoria.setCurso(curso);
                        convocatoria.setTurno(turno);
                        convocatoria.setMonto_convocatoria(rs.getInt("monto_convocatoria"));
                        convocatoria.setMonto_matricula(rs.getInt("monto_matricula"));
                                                
                        //convocatoria.setNombre_usuario(rs.getString("nombre_usuario"));
                        //System.out.println("NOM: " + usuario.getNombre_usuario());
                        listaConvocatoria.add(convocatoria);
                    }

                    //cliente.toString();
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return listaConvocatoria;

    }
    
    
    public static Convocatorias buscarId(int id) {
        Convocatorias convocatorias = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias cv "
                        + "left join cursos c on cv.id_curso=c.id_curso "
                        + "left join turnos t on cv.id_turno=t.id_turno "
                        + "where id_convocatoria=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        convocatorias = new Convocatorias();
                        convocatorias.setId_convocatoria(rs.getInt("id_convocatoria"));                                              
                        convocatorias.setFecha_convocatoria(rs.getDate("fecha_convocatoria"));
                        
                        Cursos curso = new Cursos();                        
                        curso.setId_curso(rs.getInt("id_curso"));
                        curso.setNombre_curso(rs.getString("nombre_curso"));
                        
                        Turnos turno = new Turnos();
                        turno.setId_turno(rs.getInt("id_turno"));
                        turno.setNombre_turno(rs.getString("nombre_turno"));  
                        
                        convocatorias.setMonto_convocatoria(rs.getInt("monto_convocatoria"));
                        convocatorias.setMonto_matricula(rs.getInt("monto_matricula"));                        
                        convocatorias.setCodigo_convocatoria(rs.getString("codigo_convocatoria"));                      
                        convocatorias.setConvocatoria_estado(rs.getString("convocatoria_estado"));                      
                        
                        convocatorias.setCurso(curso);
                        convocatorias.setTurno(turno);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return convocatorias;
        
    }
    
    public static Convocatorias buscarCodigoConvocatoria(String codigo) {
        Convocatorias convocatorias = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias cv "
                        + "left join cursos c on cv.id_curso=c.id_curso "
                        + "left join turnos t on cv.id_turno=t.id_turno "
                        + "where codigo_convocatoria like ? ";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setString(1, codigo);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        convocatorias = new Convocatorias();
                        convocatorias.setId_convocatoria(rs.getInt("id_convocatoria"));  
                        convocatorias.setFecha_convocatoria(rs.getDate("fecha_convocatoria"));
                        
                        Cursos curso = new Cursos();                        
                        curso.setId_curso(rs.getInt("id_curso"));
                        curso.setNombre_curso(rs.getString("nombre_curso"));
                        
                        Turnos turno = new Turnos();
                        turno.setId_turno(rs.getInt("id_turno"));
                        turno.setNombre_turno(rs.getString("nombre_turno"));  
                        
                        convocatorias.setMonto_convocatoria(rs.getInt("monto_convocatoria"));
                        convocatorias.setMonto_matricula(rs.getInt("monto_matricula"));                        
                        convocatorias.setCodigo_convocatoria(rs.getString("codigo_convocatoria"));                                                                
                        convocatorias.setCurso(curso);
                        convocatorias.setTurno(turno);
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return convocatorias;
        
    }

    
    
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias cv "
                        + "left join cursos c on cv.id_curso=c.id_curso "
                        + "left join turnos t on cv.id_turno=t.id_turno "
                        + "where cv.convocatoria_estado like 'HABILITADO' and upper(nombre_curso) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_convocatoria "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                //System.out.println("-->  CONVOCATORIA" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                                      
                    
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_convocatoria") + "</td>"                                
                                + "<td>" + rs.getString("codigo_convocatoria") + "</td>"                                
                                + "<td>" + rs.getString("nombre_curso") + "</td>"                                
                                + "<td>" + rs.getString("nombre_turno") + "</td>"   
                                + "<td>" + rs.getString("convocatoria_estado") + "</td>"   
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
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
    // CONTROLADO 2

    public static boolean agregar(Convocatorias convocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {            
            int v1 = convocatoria.getCurso().getId_curso();
            int v2 = convocatoria.getTurno().getId_turno();
            int v3 = convocatoria.getMonto_convocatoria();                
            int v4 = convocatoria.getMonto_matricula();                
            String v5= convocatoria.getCodigo_convocatoria();                 
            Date v6 = convocatoria.getFecha_convocatoria();
            String v7 = convocatoria.getConvocatoria_estado();
            
            String sql = "insert into convocatorias("
                    + "id_curso,"
                    + "id_turno,"
                    + "monto_convocatoria,"                                      
                    + "monto_matricula,"
                    + "codigo_convocatoria,"                  
                    + "fecha_convocatoria,"  
                    + "convocatoria_estado)"  
                    + " values('" + v1 
                    + "','" + v2
                    + "','" + v3 
                    + "','" + v4
                    + "','" + v5                     
                    + "','" + v6                    
                    + "','" + v7 + "')";
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_convocatoria = keyset.getInt(1);
                    convocatoria.setId_convocatoria(id_convocatoria);
                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }
        return valor;
    }

    public static boolean modificar(Convocatorias convocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update convocatorias set "
                    + "id_curso=?,"
                    + "id_turno=?,"
                    + "monto_convocatoria=?,"
                    + "monto_matricula=?,"
                    + "codigo_convocatoria=?,"                    
                    + "fecha_convocatoria=?," 
                    + "convocatoria_estado=? "
                    + "where id_convocatoria=?";
            System.out.println(sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, convocatoria.getCurso().getId_curso());
                ps.setInt(2, convocatoria.getTurno().getId_turno());
                ps.setInt(3, convocatoria.getMonto_convocatoria());
                ps.setInt(4, convocatoria.getMonto_matricula());
                ps.setString(5, convocatoria.getCodigo_convocatoria() );           
                ps.setDate(6, convocatoria.getFecha_convocatoria());
                ps.setString(7, convocatoria.getConvocatoria_estado());
                ps.setInt(8,convocatoria.getId_convocatoria());                
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
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

    public static boolean eliminar(Convocatorias convocatoria  ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from convocatorias where id_convocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, convocatoria.getId_convocatoria());
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

