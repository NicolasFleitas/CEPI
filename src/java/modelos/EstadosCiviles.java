/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;


public class EstadosCiviles {
    private int id_estadocivil;
    private String  nombre_estadocivil;

    public EstadosCiviles() {
    }

    public EstadosCiviles(int id_estadocivil, String nombre_estadocivil) {
        this.id_estadocivil = id_estadocivil;
        this.nombre_estadocivil = nombre_estadocivil;
    }

    public int getId_estadocivil() {
        return id_estadocivil;
    }

    public void setId_estadocivil(int id_estadocivil) {
        this.id_estadocivil = id_estadocivil;
    }

    public String getNombre_estadocivil() {
        return nombre_estadocivil;
    }

    public void setNombre_estadocivil(String nombre_estadocivil) {
        this.nombre_estadocivil = nombre_estadocivil;
    }

    
    
}
