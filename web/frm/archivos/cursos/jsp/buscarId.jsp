
<%@page import="controladores.CursosControlador"%>
<%@page import="modelos.Cursos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_curso = Integer.parseInt(request.getParameter("id_curso"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Cursos curso = new Cursos();
    curso.setId_curso(id_curso);
    
   CursosControlador.buscarId(curso);
    if (curso.getId_curso()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }/*else {
        curso = new Cursos();
    }*/
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_curso", curso.getId_curso());
    obj.put("nombre_curso", curso.getNombre_curso());
    out.print(obj);
    out.flush();
%>
