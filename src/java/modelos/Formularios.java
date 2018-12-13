package modelos;;

public class Formularios {
    private int id_formulario;
    private String nombre_formulario;
    private String codigo_formulario;
    private Menus menu;

    public Formularios() {
    }

    public Formularios(int id_formulario, String nombre_formulario, String codigo_formulario, Menus menu) {
        this.id_formulario = id_formulario;
        this.nombre_formulario = nombre_formulario;
        this.codigo_formulario = codigo_formulario;
        this.menu = menu;
    }

    public int getId_formulario() {
        return id_formulario;
    }

    public void setId_formulario(int id_formulario) {
        this.id_formulario = id_formulario;
    }

    public String getNombre_formulario() {
        return nombre_formulario;
    }

    public void setNombre_formulario(String nombre_formulario) {
        this.nombre_formulario = nombre_formulario;
    }

    public String getCodigo_formulario() {
        return codigo_formulario;
    }

    public void setCodigo_formulario(String codigo_formulario) {
        this.codigo_formulario = codigo_formulario;
    }

    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }
    
   
    
    
}
