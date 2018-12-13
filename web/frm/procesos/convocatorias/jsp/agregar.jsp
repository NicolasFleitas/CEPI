<%@page import="modelos.Turnos"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.ConvocatoriasControlador"%>
<%@page import="modelos.Convocatorias"%>
<%@page import="modelos.Cursos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%

    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    int id_curso = Integer.parseInt(request.getParameter("id_curso"));
    int id_turno = Integer.parseInt(request.getParameter("id_turno"));
    int monto_convocatoria = Integer.parseInt(request.getParameter("monto_convocatoria"));
    int monto_matricula = Integer.parseInt(request.getParameter("monto_matricula"));
    String convocatoria_estado = request.getParameter("convocatoria_estado");
    
    String sfecha_convocatoria = request.getParameter("fecha_convocatoria");
    
    java.sql.Date fecha_convocatoria = Utiles.stringToSqlDate(sfecha_convocatoria);  
    
    String codigo_convocatoria = (request.getParameter("codigo_convocatoria"));       
    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Cursos curso = new Cursos();
    curso.setId_curso(id_curso);

    Turnos turno = new Turnos();
    turno.setId_turno(id_turno);

    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    convocatoria.setFecha_convocatoria(fecha_convocatoria);
    convocatoria.setCurso(curso);
    convocatoria.setTurno(turno);
    convocatoria.setMonto_convocatoria(monto_convocatoria);
    convocatoria.setMonto_matricula(monto_matricula);       
    convocatoria.setCodigo_convocatoria(codigo_convocatoria);    
    convocatoria.setConvocatoria_estado(convocatoria_estado);
    
    if (ConvocatoriasControlador.agregar(convocatoria)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_convocatoria", String.valueOf(convocatoria.getId_convocatoria()));
    out.print(obj);
    out.flush();

%>