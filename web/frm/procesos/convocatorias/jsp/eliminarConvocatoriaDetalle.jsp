<%@page import="controladores.DetallesConvocatoriasControlador"%>
<%@page import="modelos.DetallesConvocatorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_detalleconvocatoria = Integer.parseInt(request.getParameter("id_detalleconvocatoria"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    DetallesConvocatorias detalleconvocatoria = new DetallesConvocatorias();
    detalleconvocatoria.setId_detalleconvocatoria(id_detalleconvocatoria);

    if (DetallesConvocatoriasControlador.eliminar(detalleconvocatoria)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>