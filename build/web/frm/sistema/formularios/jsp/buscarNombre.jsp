
<%@page import="controladores.FormulariosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_formulario = request.getParameter("bnombre_formulario");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
   
    String mensaje = "Búsqueda exitosa.";
    String contenido = FormulariosControlador.buscarNombre(nombre_formulario, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>