<%@page import="modelos.Profesores"%>
<%@page import="modelos.Convocatorias"%>
<%@page import="controladores.DetallesConvocatoriasControlador"%>
<%@page import="modelos.DetallesConvocatorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleconvocatoria = Integer.parseInt(request.getParameter("id_detalleconvocatoria"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
  
     
    DetallesConvocatorias detalleconvocatoria = new DetallesConvocatorias();
    detalleconvocatoria.setId_detalleconvocatoria(id_detalleconvocatoria);
   
    
  DetallesConvocatoriasControlador.buscarId(detalleconvocatoria);
    
    
    if (detalleconvocatoria.getId_detalleconvocatoria() != 0) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_detalleconvocatoria",detalleconvocatoria.getId_detalleconvocatoria());        
    obj.put("id_profesor", detalleconvocatoria.getProfesor().getId_profesor()); 
    obj.put("nombre_profesor", detalleconvocatoria.getProfesor().getNombre_profesor()); 
    
    //System.out.println("---> id_profesor " + detalleconvocatoria.getProfesor().getId_profesor());
    
    out.print(obj);
    out.flush();
%>