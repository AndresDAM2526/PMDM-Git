package modelos;

import java.util.ArrayList;

public class GestorUsuarios implements GestionUsuarios {

    public ArrayList<Usuario> usuarios = new ArrayList<>();

    @Override
    public void agregarUsuario(Usuario usuario) {
        for(Usuario us:usuarios){
            if(us.getID()==usuario.getID()){
                throw new RuntimeException("Este usuario ya existe");
            }else{
                usuarios.add(usuario);
            }
        }
    }

    @Override
    public void eliminarUsuaios(int idUsuario) {
        boolean encontrado=false;
        Usuario usuarioEncontrado=null;
        for(Usuario us:usuarios){
            if(us.getID()==idUsuario){
                encontrado=true;
                usuarioEncontrado=us;
                break;
            }
        }
        if(encontrado){
            usuarios.remove(usuarioEncontrado);
        }else{
            throw new RuntimeException("Este usuario no se ha encontrado");
        }
    }

    @Override
    public ArrayList<String> listarUsuario(int tipoUsuario) {
        ArrayList<String> usuariosEncontrados=new ArrayList<>();
        switch (tipoUsuario){
            case 0:
                for(Usuario us:usuarios){
                    if (us instanceof Estudiante){
                        usuariosEncontrados.add(us.getNombre());
                    }
                }
                break;
            case 1:
                for (Usuario us:usuarios){
                    if(us instanceof Profesor){
                        usuariosEncontrados.add(us.getNombre());
                    }
                }
                break;
        }
        return usuariosEncontrados;
    }

    public Usuario existeUsuario(int idUsuario){
        for (Usuario us:usuarios){
            if(us.getID()==idUsuario){
                return us;
            }
        }
        return null;
    }
}
