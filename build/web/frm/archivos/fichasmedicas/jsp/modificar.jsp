<%@page import="modelos.Alumnos"%>
<%@page import="controladores.FichasMedicasControlador"%>
<%@page import="modelos.FichasMedicas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_fichamedica = Integer.parseInt(request.getParameter("id_fichamedica"));
    //String nombre_fichamedica = request.getParameter("nombre_fichamedica");
    int id_alumno = Integer.parseInt(request.getParameter("id_alumno"));    
    int peso_nacimiento = Integer.parseInt(request.getParameter("peso_nacimiento"));
    int peso_actual = Integer.parseInt(request.getParameter("peso_actual"));
    String tipo_parto = request.getParameter("tipo_parto");
    String descrip_embarazo = request.getParameter("descripcion_embarazo");
    String descrip_vacunas = request.getParameter("descripcion_vacunas");
    String descrip_alergias = request.getParameter("descripcion_alergias");
    String descrip_enfermedades = request.getParameter("descripcion_enfermedades");
    String obs_importantes = request.getParameter("obs_importantes");
    
    
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Alumnos alumno = new Alumnos();
    alumno.setId_alumno(id_alumno);
    
    FichasMedicas fichamedica = new FichasMedicas();
    fichamedica.setId_fichamedica(id_fichamedica);        
    fichamedica.setPeso_nacimiento(peso_nacimiento);
    fichamedica.setPeso_actual(peso_actual);
    fichamedica.setTipo_parto(tipo_parto);     
    fichamedica.setDescrip_alergias(descrip_alergias);
    fichamedica.setDescrip_vacunas(descrip_vacunas);
    fichamedica.setDescrip_embarazo(descrip_embarazo);
    fichamedica.setDescrip_enfermedades(descrip_enfermedades);
    fichamedica.setObs_importantes(obs_importantes);    
    
    fichamedica.setAlumno(alumno);

    if (FichasMedicasControlador.modificar(fichamedica)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>

