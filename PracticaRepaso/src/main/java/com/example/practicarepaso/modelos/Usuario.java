package com.example.practicarepaso.modelos;

abstract public class Usuario {


    protected int id;
    protected String nombre;
    protected String email;
    protected String password;



    public Usuario(int id, String nombre, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
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
        return "ID: " + this.id + "\nNombre: " + this.nombre + "\nEmail: " + this.email+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}