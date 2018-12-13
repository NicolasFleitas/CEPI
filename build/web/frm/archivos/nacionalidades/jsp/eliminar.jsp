
<%@page import="controladores.NacionalidadesControlador"%>
<%@page import="modelos.Nacionalidades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Nacionalidades nacionalidad = new Nacionalidades();
    nacionalidad.setId_nacionalidad(id_nacionalidad);
    
    if (NacionalidadesControlador.eliminar(nacionalidad)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>