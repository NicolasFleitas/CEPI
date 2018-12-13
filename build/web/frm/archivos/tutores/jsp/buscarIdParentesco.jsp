
<%@page import="controladores.ParentescosControlador"%>
<%@page import="modelos.Parentescos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_parentesco = Integer.parseInt(request.getParameter("id_parentesco"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Parentescos parentesco = new Parentescos();
    parentesco.setId_parentesco(id_parentesco);
    
   ParentescosControlador.buscarId(parentesco);
    if (parentesco.getId_parentesco()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        parentesco = new Parentescos();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_parentesco", parentesco.getId_parentesco());
    obj.put("nombre_parentesco", parentesco.getNombre_parentesco());
    out.print(obj);
    out.flush();
%>
