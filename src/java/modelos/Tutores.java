package modelos;

import java.sql.Date;

public class Tutores {
    private int id_tutor;
    private String nombre_tutor;
    private String apellido_tutor;
    private String ruc_tutor;
    private String direccion_tutor; 
    private int telefono_tutor;
    private String email;
    private EstadosCiviles estadocivil;
    private Ciudades ciudad;    
    private Nacionalidades nacionalidad;
    private String profesion_tutor;
    private String ocupacion_tutor;
    private String direccion_laboral_tutor;
    private Date fecha_nac_tutor;
    private String telefono_laboral_tutor;
    private Parentescos parentesco;
    
    public Tutores() {
    }

    public Tutores(int id_tutor, String nombre_tutor, String apellido_tutor, String ruc_tutor, String direccion_tutor, int telefono_tutor, String email, EstadosCiviles estadocivil, Ciudades ciudad, Nacionalidades nacionalidad, String profesion_tutor, String ocupacion_tutor, String direccion_laboral_tutor, Date fecha_nac_tutor, String telefono_laboral_tutor, Parentescos parentesco) {
        this.id_tutor = id_tutor;
        this.nombre_tutor = nombre_tutor;
        this.apellido_tutor = apellido_tutor;
        this.ruc_tutor = ruc_tutor;
        this.direccion_tutor = direccion_tutor;
        this.telefono_tutor = telefono_tutor;
        this.email = email;
        this.estadocivil = estadocivil;
        this.ciudad = ciudad;
        this.nacionalidad = nacionalidad;
        this.profesion_tutor = profesion_tutor;
        this.ocupacion_tutor = ocupacion_tutor;
        this.direccion_laboral_tutor = direccion_laboral_tutor;
        this.fecha_nac_tutor = fecha_nac_tutor;
        this.telefono_laboral_tutor = telefono_laboral_tutor;
        this.parentesco = parentesco;
    }



    public int getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(int id_tutor) {
        this.id_tutor = id_tutor;
    }

    public String getNombre_tutor() {
        return nombre_tutor;
    }

    public void setNombre_tutor(String nombre_tutor) {
        this.nombre_tutor = nombre_tutor;
    }

    public String getApellido_tutor() {
        return apellido_tutor;
    }

    public void setApellido_tutor(String apellido_tutor) {
        this.apellido_tutor = apellido_tutor;
    }

    public String getRuc_tutor() {
        return ruc_tutor;
    }

    public void setRuc_tutor(String ruc_tutor) {
        this.ruc_tutor = ruc_tutor;
    }

    public String getDireccion_tutor() {
        return direccion_tutor;
    }

    public void setDireccion_tutor(String direccion_tutor) {
        this.direccion_tutor = direccion_tutor;
    }

    public int getTelefono_tutor() {
        return telefono_tutor;
    }

    public void setTelefono_tutor(int telefono_tutor) {
        this.telefono_tutor = telefono_tutor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EstadosCiviles getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(EstadosCiviles estadocivil) {
        this.estadocivil = estadocivil;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    public Nacionalidades getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidades nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getProfesion_tutor() {
        return profesion_tutor;
    }

    public void setProfesion_tutor(String profesion_tutor) {
        this.profesion_tutor = profesion_tutor;
    }

    public String getOcupacion_tutor() {
        return ocupacion_tutor;
    }

    public void setOcupacion_tutor(String ocupacion_tutor) {
        this.ocupacion_tutor = ocupacion_tutor;
    }

    public String getDireccion_laboral_tutor() {
        return direccion_laboral_tutor;
    }

    public void setDireccion_laboral_tutor(String direccion_laboral_tutor) {
        this.direccion_laboral_tutor = direccion_laboral_tutor;
    }

    public Date getFecha_nac_tutor() {
        return fecha_nac_tutor;
    }

    public void setFecha_nac_tutor(Date fecha_nac_tutor) {
        this.fecha_nac_tutor = fecha_nac_tutor;
    }

  

    public String getTelefono_laboral_tutor() {
        return telefono_laboral_tutor;
    }

    public void setTelefono_laboral_tutor(String telefono_laboral_tutor) {
        this.telefono_laboral_tutor = telefono_laboral_tutor;
    }

    public Parentescos getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentescos parentesco) {
        this.parentesco = parentesco;
    }
    
}
