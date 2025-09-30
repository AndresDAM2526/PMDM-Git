package modelos;

import java.util.ArrayList;

public interface GestionUsuarios {
    abstract public void agregarUsuario(Usuario us);
    abstract public void eliminarUsuaios(int idUsuario);
    abstract public ArrayList<String> listarUsuario(int tipoUsuario);
}
