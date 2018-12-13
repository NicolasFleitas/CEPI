
<%@page import="controladores.SexosControlador"%>
<%@page import="modelos.Sexos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_sexo = Integer.parseInt(request.getParameter("id_sexo"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Sexos sexo = new Sexos();
    sexo.setId_sexo(id_sexo);
    
    if (SexosControlador.eliminar(sexo)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>