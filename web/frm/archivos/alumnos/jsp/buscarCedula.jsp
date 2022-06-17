<%@page import="org.json.simple.JSONObject"%>
<%@page import="modelos.Alumnos"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="controladores.AlumnosControlador"%>
<%

    int nroci_alumno = Integer.parseInt(request.getParameter("nroci_alumno"));

    String tipo = "error";
 
    Alumnos alumno = new Alumnos();
    alumno.setNroci_alumno(nroci_alumno);
    AlumnosControlador.buscarCedula(alumno);


    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("nroci_alumno", alumno.getNroci_alumno());
    //System.out.println(alumno.getNroci_alumno());
    out.print(obj);
    out.flush();
%>