
<%@page import="controladores.AlumnosControlador"%>
<%@page import="modelos.Alumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_alumno = Integer.parseInt(request.getParameter("id_alumno"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Alumnos alumno = new Alumnos();
    alumno.setId_alumno(id_alumno);
    
   AlumnosControlador.buscarId(alumno);
    if (alumno.getId_alumno()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    /*else {
        alumno = new Alumnos();
    }    */
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_alumno", alumno.getId_alumno());
    obj.put("nombre_alumno", alumno.getNombre_alumno());
    obj.put("apellido_alumno", alumno.getApellido_alumno());
    obj.put("nroci_alumno", alumno.getNroci_alumno());
    obj.put("fecha_nac_alumno", alumno.getFecha_nac_alumno());    
    obj.put("nombre_medico", alumno.getNombre_medico());
    obj.put("telefono_medico", alumno.getTelefono_medico());
    
    obj.put("id_sexo", alumno.getSexo().getId_sexo());
    obj.put("nombre_sexo", alumno.getSexo().getNombre_sexo());
    out.print(obj);
    out.flush();
%>
