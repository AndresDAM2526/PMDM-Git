package com.example.practicarepaso.modelos;

import java.util.ArrayList;

public interface GestionUsuarios {
    abstract public void agregarUsuario(Usuario us);
    abstract public void eliminarUsuaios(int idUsuario);
    abstract public ArrayList<Usuario> listarUsuario(int tipoUsuario);
}
