package modelos;
public class FichasMedicas {
    private int id_fichamedica;    
    private String fecha_nac;
    private int peso_actual;
    private int peso_nacimiento;
    private String tipo_parto;    
    private String descrip_embarazo;
    private String descrip_vacunas;
    private String descrip_alergias;
    private String descrip_enfermedades;
    private String obs_importantes;
    
    private Alumnos alumno;
    
    public FichasMedicas(){
        
    }

    public FichasMedicas(int id_fichamedica, String fecha_nac, int peso_actual, int peso_nacimiento, String tipo_parto, String descrip_embarazo, String descrip_vacunas, String descrip_alergias, String descrip_enfermedades, String obs_importantes, Alumnos alumno) {
        this.id_fichamedica = id_fichamedica;
        this.fecha_nac = fecha_nac;
        this.peso_actual = peso_actual;
        this.peso_nacimiento = peso_nacimiento;
        this.tipo_parto = tipo_parto;
        this.descrip_embarazo = descrip_embarazo;
        this.descrip_vacunas = descrip_vacunas;
        this.descrip_alergias = descrip_alergias;
        this.descrip_enfermedades = descrip_enfermedades;
        this.obs_importantes = obs_importantes;
        this.alumno = alumno;
    }

    public int getId_fichamedica() {
        return id_fichamedica;
    }

    public void setId_fichamedica(int id_fichamedica) {
        this.id_fichamedica = id_fichamedica;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public int getPeso_actual() {
        return peso_actual;
    }

    public void setPeso_actual(int peso_actual) {
        this.peso_actual = peso_actual;
    }

    public int getPeso_nacimiento() {
        return peso_nacimiento;
    }

    public void setPeso_nacimiento(int peso_nacimiento) {
        this.peso_nacimiento = peso_nacimiento;
    }

    public String getTipo_parto() {
        return tipo_parto;
    }

    public void setTipo_parto(String tipo_parto) {
        this.tipo_parto = tipo_parto;
    }

    public String getDescrip_embarazo() {
        return descrip_embarazo;
    }

    public void setDescrip_embarazo(String descrip_embarazo) {
        this.descrip_embarazo = descrip_embarazo;
    }

    public String getDescrip_vacunas() {
        return descrip_vacunas;
    }

    public void setDescrip_vacunas(String descrip_vacunas) {
        this.descrip_vacunas = descrip_vacunas;
    }

    public String getDescrip_alergias() {
        return descrip_alergias;
    }

    public void setDescrip_alergias(String descrip_alergias) {
        this.descrip_alergias = descrip_alergias;
    }

    public String getDescrip_enfermedades() {
        return descrip_enfermedades;
    }

    public void setDescrip_enfermedades(String descrip_enfermedades) {
        this.descrip_enfermedades = descrip_enfermedades;
    }

    public String getObs_importantes() {
        return obs_importantes;
    }

    public void setObs_importantes(String obs_importantes) {
        this.obs_importantes = obs_importantes;
    }

    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos alumno) {
        this.alumno = alumno;
    }
    
    
    
    
    
    
    
}
