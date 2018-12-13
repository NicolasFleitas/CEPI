
<%@page import="controladores.ParentescosControlador"%>
<%@page import="modelos.Parentescos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_parentesco = Integer.parseInt(request.getParameter("id_parentesco"));
    String nombre_parentesco = request.getParameter("nombre_parentesco");

    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Parentescos parentesco = new Parentescos();
    parentesco.setId_parentesco(id_parentesco);
    parentesco.setNombre_parentesco(nombre_parentesco);

    if (ParentescosControlador.agregar(parentesco)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

