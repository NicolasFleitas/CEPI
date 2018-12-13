
<%@page import="controladores.AlumnosControlador"%>
<%@page import="modelos.Alumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_alumno = Integer.parseInt(request.getParameter("id_alumno"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Alumnos alumno = new Alumnos();
    alumno.setId_alumno(id_alumno);
    
    if (AlumnosControlador.eliminar(alumno)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>