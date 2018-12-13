/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

public class Turnos {
 private int id_turno  ;
 private String nombre_turno;

    public Turnos() {
    }

    public Turnos(int id_turno, String nombre_turno) {
        this.id_turno = id_turno;
        this.nombre_turno = nombre_turno;
    }

    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public String getNombre_turno() {
        return nombre_turno;
    }

    public void setNombre_turno(String nombre_turno) {
        this.nombre_turno = nombre_turno;
    }
    
    

   
 
}
