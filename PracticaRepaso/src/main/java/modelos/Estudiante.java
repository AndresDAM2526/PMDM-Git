package modelos;

public class Estudiante extends Usuario{

    public String curso;

    public Estudiante(int id, String nombre, String email, String password,String curso){
        super(id,nombre,email,password);
        this.curso=curso;
    }
}
