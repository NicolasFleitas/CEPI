
<%@page import="controladores.NacionalidadesControlador"%>
<%@page import="modelos.Nacionalidades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Nacionalidades nacionalidad = new Nacionalidades();
    nacionalidad.setId_nacionalidad(id_nacionalidad);
    
   NacionalidadesControlador.buscarId(nacionalidad);
    if (nacionalidad.getId_nacionalidad()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        nacionalidad = new Nacionalidades();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_nacionalidad", nacionalidad.getId_nacionalidad());
    obj.put("nombre_nacionalidad", nacionalidad.getNombre_nacionalidad());
    out.print(obj);
    out.flush();
%>
