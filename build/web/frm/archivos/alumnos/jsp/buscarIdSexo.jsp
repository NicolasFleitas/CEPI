
<%@page import="controladores.SexosControlador"%>
<%@page import="modelos.Sexos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_sexo = Integer.parseInt(request.getParameter("id_sexo"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Sexos sexo = new Sexos();
    sexo.setId_sexo(id_sexo);
    
   SexosControlador.buscarId(sexo);
    if (sexo.getId_sexo()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        sexo = new Sexos();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_sexo", sexo.getId_sexo());
    obj.put("nombre_sexo", sexo.getNombre_sexo());
    out.print(obj);
    out.flush();
%>
