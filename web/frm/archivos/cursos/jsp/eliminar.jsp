
<%@page import="controladores.CursosControlador"%>
<%@page import="modelos.Cursos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_curso = Integer.parseInt(request.getParameter("id_curso"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Cursos curso = new Cursos();
    curso.setId_curso(id_curso);
    
    if (CursosControlador.eliminar(curso)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>