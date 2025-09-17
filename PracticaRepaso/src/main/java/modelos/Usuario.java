package modelos;

abstract public class Usuario {


    protected int id;
    protected String nombre;
    protected String email;
    protected String password;

    public Usuario() {
        this.id = id;
        this.nombre = nombre;
    }


    public int getID() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(int idNuevo) {
        this.id = idNuevo;
    }

    public void setNombre(String nombreNuevo) {
        this.nombre = nombreNuevo;
    }

    public void setEmail(String emailNuevo) {
        this.email = emailNuevo;
    }

    public void setPassword(String passNuevo) {
        this.password = passNuevo;
    }

    public String toString() {
        return "ID: " + this.id + "\nNombre: " + this.nombre + "\nEmail: " + this.email;
    }

}