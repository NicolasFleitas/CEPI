/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author nicof
 */
public class Profesores {
    private int id_profesor;
    private String nombre_profesor;
    private String apellido_profesor;
    private String nroci_profesor;
    private String telefono_profesor;
    private Cursos curso;

    public Profesores() {
    }

    public Profesores(int id_profesor, String nombre_profesor, String apellido_profesor, String nroci_profesor, String telefono_profesor, Cursos curso) {
        this.id_profesor = id_profesor;
        this.nombre_profesor = nombre_profesor;
        this.apellido_profesor = apellido_profesor;
        this.nroci_profesor = nroci_profesor;
        this.telefono_profesor = telefono_profesor;
        this.curso = curso;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public String getNombre_profesor() {
        return nombre_profesor;
    }

    public void setNombre_profesor(String nombre_profesor) {
        this.nombre_profesor = nombre_profesor;
    }

    public String getApellido_profesor() {
        return apellido_profesor;
    }

    public void setApellido_profesor(String apellido_profesor) {
        this.apellido_profesor = apellido_profesor;
    }

    public String getNroci_profesor() {
        return nroci_profesor;
    }

    public void setNroci_profesor(String nroci_profesor) {
        this.nroci_profesor = nroci_profesor;
    }

    public String getTelefono_profesor() {
        return telefono_profesor;
    }

    public void setTelefono_profesor(String telefono_profesor) {
        this.telefono_profesor = telefono_profesor;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

   
    
    
    
}
