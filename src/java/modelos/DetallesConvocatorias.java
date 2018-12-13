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
public class DetallesConvocatorias {
    private int id_detalleconvocatoria;
    private Convocatorias convocatoria;
    private Profesores profesor;    
    

    public DetallesConvocatorias() {
    }

    public DetallesConvocatorias(int id_detalleconvocatoria, Convocatorias convocatoria, Profesores profesor) {
        this.id_detalleconvocatoria = id_detalleconvocatoria;
        this.convocatoria = convocatoria;
        this.profesor = profesor;
    }

    public int getId_detalleconvocatoria() {
        return id_detalleconvocatoria;
    }

    public void setId_detalleconvocatoria(int id_detalleconvocatoria) {
        this.id_detalleconvocatoria = id_detalleconvocatoria;
    }

    public Convocatorias getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatorias convocatoria) {
        this.convocatoria = convocatoria;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }

    

    
    
}
