
<%@page import="controladores.NacionalidadesControlador"%>
<%@page import="modelos.Nacionalidades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));
    String nombre_nacionalidad = request.getParameter("nombre_nacionalidad");
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Nacionalidades nacionalidad = new Nacionalidades();
    nacionalidad.setId_nacionalidad(id_nacionalidad);
    nacionalidad.setNombre_nacionalidad(nombre_nacionalidad);
    
    if (NacionalidadesControlador.agregar(nacionalidad)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

