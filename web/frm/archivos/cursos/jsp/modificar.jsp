<%@page import="controladores.CursosControlador"%>
<%@page import="modelos.Cursos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_curso = Integer.parseInt(request.getParameter("id_curso"));
    String nombre_curso = request.getParameter("nombre_curso");
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Cursos curso = new Cursos();
    curso.setId_curso(id_curso);
    curso.setNombre_curso(nombre_curso);

    if (CursosControlador.modificar(curso)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
