package com.example.practicarepaso.modelos;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class GestorUsuarios implements GestionUsuarios {

    public ArrayList<Usuario> usuarios = new ArrayList<>();

    @Override
    public void agregarUsuario(Usuario usuario) {
        for (Usuario us : usuarios) {
            if (us.getID() == usuario.getID()) {
                throw new RuntimeException("Este usuario ya existe");
            }
        }
        usuarios.add(usuario);
    }

    @Override
    public void eliminarUsuaios(int idUsuario) {
        boolean encontrado = false;
        Usuario usuarioEncontrado = null;
        for (Usuario us : usuarios) {
            if (us.getID() == idUsuario) {
                encontrado = true;
                usuarioEncontrado = us;
                break;
            }
        }
        if (encontrado) {
            usuarios.remove(usuarioEncontrado);
        } else {
            throw new RuntimeException("Este usuario no se ha encontrado");
        }
    }

    @Override
    public ArrayList<Usuario> listarUsuario(int tipoUsuario) {
        ArrayList<Usuario> usuariosEncontrados = new ArrayList<>();
        switch (tipoUsuario) {
            case 0:
                for (Usuario us : usuarios) {
                    if (us instanceof Estudiante) {
                        usuariosEncontrados.add(us);
                    }
                }
                break;
            case 1:
                for (Usuario us : usuarios) {
                    if (us instanceof Profesor) {
                        usuariosEncontrados.add(us);
                    }
                }
                break;
        }
        return usuariosEncontrados;
    }

    public Usuario existeUsuario(int idUsuario) {
        for (Usuario us : usuarios) {
            if (us.getID() == idUsuario) {
                return us;
            }
        }
        return null;
    }

    public void generarNomina(LocalDate fecha, int idUsuario) {
        Profesor profesorEncontrado = (Profesor) existeUsuario(idUsuario);
        String nombreFichero = profesorEncontrado.getID() + "_" + profesorEncontrado.getNombre() + "_" + fecha;
        File directorio = new File("src/main/resources/nominas");
        File fichero = new File(directorio, nombreFichero);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.write("Nombre: " + profesorEncontrado.getNombre() + "\t\tEspecialidad: " + profesorEncontrado.getEspecialidad() + "\n");
            bw.write("Cargo: " + profesorEncontrado.getCargo() + "\t\tAntigüedad: " + profesorEncontrado.getFecha_comienzo() + "\n");
            bw.write("Mes: " + LocalDate.now().getMonth() + "  Año: " + LocalDate.now().getYear() + "\n");
            bw.write("CONCEPTO" + "\tImporte\n");
            bw.write("--------------------------------------------------\n");
            bw.write("Salario base:" + "\t1500€\n");
            if (profesorEncontrado.getCargo().equals(Cargo.Director)) {
                bw.write("Complemento cargo: " + "\t500€\n");
            } else if (profesorEncontrado.getCargo().equals(Cargo.Secretario)) {
                bw.write("Complemento cargo: " + "\t300€\n");
            }
            bw.write("Antigüedad: " + "\t" + profesorEncontrado.complementoAntiguedad(profesorEncontrado.fecha_comienzo) + "\n");
            bw.write("--------------------------------------------------\n");
            bw.write("TOTAL(Bruto)" + "\t(" + profesorEncontrado.calcular_salario_bruto(fecha) + "€" + ")\n");
            bw.write("IRPF" + "\t" + profesorEncontrado.getIrpf() + "\n");
            bw.write("TOTAL(Neto,a percibir" + "\t(" + profesorEncontrado.calcular_salario_neto(profesorEncontrado.calcular_salario_bruto(fecha)) + "€)\n");
            bw.close();

        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error entrada/salida");
            e.printStackTrace();
        }
    }

    public boolean validarUsuario(String email, String pass) {
        try {
            boolean datosCorrectos = false;
            for (Usuario us : usuarios) {
                if (us.getEmail().equals(email) && us.getPassword().equals(pass)) {
                    datosCorrectos = true;
                    break;
                }
            }
            return datosCorrectos;
        } catch (NullPointerException e) {
            System.out.println("Datos vacios");
            return false;
        } catch (InputMismatchException e) {
            System.out.println("Tipo de dato erroneo");
            return false;
        }

    }
}
