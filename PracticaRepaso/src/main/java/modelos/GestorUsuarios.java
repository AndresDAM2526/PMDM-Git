package modelos;

import java.util.ArrayList;

public class GestorUsuarios implements GestionUsuarios {

    public ArrayList<Usuario> usuarios = new ArrayList<>();

    @Override
    public void agregarUsuario(Usuario us) {
        if (us != null) {
            usuarios.add(us);
        }
    }

    @Override
    public void eliminarUsuaios(int idUsuario) {
        for(Usuario us:usuarios){
            if(us.getID()!=idUsuario){
                throw new Exception("El identificador indicado no está registrado");
            }else{
                usuarios.remove()
            }
        }
    }
}
