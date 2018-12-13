package modelos;;
import org.json.simple.JSONObject;
public class Usuarios {
    private int id_usuario;
    private String nombre_usuario;
    private String login_usuario;
    private String password_usuario;
    private Roles rol;

    public Usuarios() {
    }
    public Usuarios(int id_usuario, String nombre_usuario, String login_usuario, String password_usuario, Roles rol) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.login_usuario = login_usuario;
        this.password_usuario = password_usuario;
        this.rol = rol;
    }
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getPassword_usuario() {
        return password_usuario;
    }

    public void setPassword_usuario(String password_usuario) {
        this.password_usuario = password_usuario;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }
    
     public JSONObject getJSONString() {
        JSONObject obj = new JSONObject();
        obj.put("id_usuario", this.id_usuario);
        obj.put("nombre_usuario", this.nombre_usuario);       
        return obj;
    }


}
