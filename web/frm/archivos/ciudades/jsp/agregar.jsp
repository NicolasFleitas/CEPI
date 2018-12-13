
<%@page import="controladores.CiudadesControlador"%>
<%@page import="modelos.Ciudades"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
    String nombre_ciudad = request.getParameter("nombre_ciudad");

    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    ciudad.setNombre_ciudad(nombre_ciudad);

    if (CiudadesControlador.agregar(ciudad)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

