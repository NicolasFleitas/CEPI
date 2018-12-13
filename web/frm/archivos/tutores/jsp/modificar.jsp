<%@page import="utiles.Utiles"%>
<%@page import="modelos.Ciudades"%>
<%@page import="modelos.Nacionalidades"%>
<%@page import="modelos.EstadosCiviles"%>
<%@page import="modelos.Parentescos"%>
<%@page import="controladores.TutoresControlador"%>
<%@page import="modelos.Tutores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%        
     int id_tutor =         Integer.parseInt(request.getParameter("id_tutor"));
     int id_nacionalidad =  Integer.parseInt(request.getParameter("id_nacionalidad"));
     int id_ciudad =        Integer.parseInt(request.getParameter("id_ciudad"));     
     int id_estadocivil =   Integer.parseInt(request.getParameter("id_estadocivil"));
     int id_parentesco =    Integer.parseInt(request.getParameter("id_parentesco"));
             
    String nombre_tutor = request.getParameter("nombre_tutor");
    String apellido_tutor = request.getParameter("apellido_tutor");
    String ruc_tutor = request.getParameter("ruc_tutor");
    int telefono_tutor = Integer.parseInt(request.getParameter("telefono_tutor"));
    String direccion_tutor = request.getParameter("direccion_tutor");
    String email_tutor = request.getParameter("email_tutor");
    
   String sfecha_nac_tutor = request.getParameter("fecha_nac_tutor");
   java.sql.Date fecha_nac_tutor = Utiles.stringToSqlDate(sfecha_nac_tutor);    
    
    String profesion_tutor = request.getParameter("profesion_tutor");
    String ocupacion_tutor = request.getParameter("ocupacion_tutor");
    String direccion_laboral_tutor = request.getParameter("direccion_laboral_tutor");
    String telefono_laboral_tutor = request.getParameter("telefono_laboral_tutor");
    
    
    Nacionalidades nacionalidad = new Nacionalidades();
    Ciudades ciudad = new Ciudades();
    EstadosCiviles estadocivil = new EstadosCiviles();
   Parentescos parentesco = new Parentescos();
    
    nacionalidad.setId_nacionalidad(id_nacionalidad);
    ciudad.setId_ciudad(id_ciudad);
    estadocivil.setId_estadocivil(id_estadocivil);
    parentesco.setId_parentesco(id_parentesco);
    
   

    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Tutores tutor = new Tutores();
    tutor.setId_tutor(id_tutor);
    tutor.setNombre_tutor(nombre_tutor);
    tutor.setApellido_tutor(apellido_tutor);
    tutor.setRuc_tutor(ruc_tutor);
    tutor.setTelefono_tutor(telefono_tutor);
    tutor.setDireccion_tutor(direccion_tutor);
    tutor.setEmail(email_tutor);
    tutor.setFecha_nac_tutor(fecha_nac_tutor);
    tutor.setProfesion_tutor(profesion_tutor);
    tutor.setOcupacion_tutor(ocupacion_tutor);
    tutor.setDireccion_laboral_tutor(direccion_laboral_tutor);
    tutor.setTelefono_laboral_tutor(telefono_laboral_tutor); 
    
    tutor.setNacionalidad(nacionalidad);
    tutor.setCiudad(ciudad);
    tutor.setEstadocivil(estadocivil);
    tutor.setParentesco(parentesco);
   
    if (TutoresControlador.modificar(tutor)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
