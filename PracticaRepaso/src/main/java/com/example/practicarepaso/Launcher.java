package com.example.practicarepaso;

import com.example.practicarepaso.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.practicarepaso.modelos.*;
import com.example.practicarepaso.util.R;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Launcher extends Application{

    public static GestorUsuarios gestorUsuarios=new GestorUsuarios();
    public static void main(String[] args) {

        Scanner teclado=new Scanner(System.in);

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            int opcionUsuario=-1;
            int tipoUsuario;
            while(opcionUsuario!=5){
                menuPrincipal();
                opcionUsuario=teclado.nextInt();
                switch (opcionUsuario){
                    case 1: //Dar de alta un usuario
                        int idUsuario;
                        String nombreUsuario;
                        String email;
                        String pass;
                        String curso;
                        System.out.println("---Dar de alta al usuario---");
                        tipoUsuarioMenu();
                        tipoUsuario=teclado.nextInt();
                        switch (tipoUsuario){
                            case 0: //Estudiante
                                System.out.print("Introduzca el ID del usuario:");
                                idUsuario=teclado.nextInt();
                                teclado.nextLine();
                                System.out.print("Introduzca el nombre:");
                                nombreUsuario=teclado.nextLine();
                                System.out.print("Introduzca el email:");
                                email=teclado.nextLine();
                                System.out.print("Introduzca la contraseña:");
                                pass=teclado.nextLine();
                                System.out.print("Introduzca el curso:");
                                curso=teclado.nextLine();
                                Estudiante est=new Estudiante(idUsuario,nombreUsuario,email,pass,curso);
                                gestorUsuarios.agregarUsuario(est);
                                Thread.sleep(500);
                                System.out.println("Estudiante añadido correctamente");
                                break;
                            case 1: //Profesor
                                String especialidad="";
                                String fechaComienzo="";
                                LocalDate fechaComienzoLd;
                                int irpf;
                                String cargoStr;
                                Cargo cargo;
                                System.out.print("Introduzca el ID del usuario:");
                                idUsuario=teclado.nextInt();
                                teclado.nextLine();
                                System.out.print("Introduzca el nombre:");
                                nombreUsuario=teclado.nextLine();
                                System.out.print("Introduzca el email:");
                                email=teclado.nextLine();
                                System.out.print("Introduzca la contraseña:");
                                pass=teclado.nextLine();
                                System.out.print("Introduzca la especialidad:");
                                especialidad=teclado.nextLine();
                                System.out.print("Introduzca su fecha de alta:");
                                fechaComienzo=teclado.nextLine();
                                fechaComienzoLd=LocalDate.parse(fechaComienzo,formatter);
                                if(fechaComienzoLd.isAfter(LocalDate.now())){
                                    System.out.println("La fecha de fecha es incorrecta, se iniciará el proceso de nuevo");
                                    break;
                                }
                                System.out.print("Intoduzca el IRPF:");
                                irpf=teclado.nextInt();
                                teclado.nextLine();
                                System.out.print("Introduzca su cargo(Director,Secretario,Profesor):");
                                cargoStr=teclado.nextLine();
                                cargo=Cargo.valueOf(cargoStr);
                                Profesor pf=new Profesor(idUsuario,nombreUsuario,email,pass,especialidad,fechaComienzoLd,irpf,cargo);
                                gestorUsuarios.agregarUsuario(pf);
                                System.out.println("Trabajador añadido correctamente");
                                break;
                            default:
                                System.out.println("Opción incorrecta");
                                break;
                        }
                        break;
                    case 2://Mostrar usuarios
                        try {
                            ArrayList<Usuario> usuariosEncontrados=new ArrayList<>();
                            System.out.println("---Listar usuarios---");
                            tipoUsuarioMenu();
                            int tipoUsuarioListar=teclado.nextInt();
                            switch (tipoUsuarioListar){
                                case 0:
                                    usuariosEncontrados=gestorUsuarios.listarUsuario(0);
                                    System.out.println(usuariosEncontrados);
                                    break;
                                case 1:
                                    usuariosEncontrados=gestorUsuarios.listarUsuario(1);
                                    System.out.println(usuariosEncontrados);
                                    break;
                                default:
                                    System.out.println("Opción incorrecta");
                                    break;
                            }
                        }catch (InputMismatchException e){
                            System.out.println("Tipo de dato introducido incorrecto");
                        }
                        break;
                    case 3://Eliminar usuario
                        try {
                            System.out.println("---Eliminar usuario---");
                            System.out.println("Introduzca el ID del usuario que quiere eliminar");
                            int idUsuarioEliminar=teclado.nextInt();
                            gestorUsuarios.eliminarUsuaios(idUsuarioEliminar);

                        }catch (InputMismatchException e){
                            System.out.println("Tipo de dato introducido incorrecto");
                        }
                        break;
                    case 4: //Establecer IRPF
                        try {
                            int irpfSalario;
                            System.out.println("---Configurar salario---");
                            System.out.println("Introduzca el ID del usuario:");
                            int idUsuarioSalario=teclado.nextInt();
                            Profesor profesorEncontrado=(Profesor) gestorUsuarios.existeUsuario(1);
                            if(profesorEncontrado!=null){
                                System.out.println("Introduzca el IRPF:");
                                irpfSalario=teclado.nextInt();
                                profesorEncontrado.setIrpf(irpfSalario);
                            }
                        }catch (InputMismatchException e){
                            System.out.println("Tipo de dato introducido incorrecto");
                        }
                        break;
                    case 5: //Generar nomina
                        int idUsuarioNomina;
                        System.out.println("---Generar nómina---");
                        System.out.print("Introduzca el ID del usuario:");
                        idUsuarioNomina=teclado.nextInt();
                        Usuario usuarioEncontrado=gestorUsuarios.existeUsuario(idUsuarioNomina);
                        if(usuarioEncontrado!=null) {
                            Profesor profesorEncontrado=(Profesor) usuarioEncontrado;
                            gestorUsuarios.generarNomina(profesorEncontrado.fecha_comienzo, idUsuarioNomina);
                            break;
                        }else {
                            System.out.println("No se ha encontrado el profesor");
                            break;
                        }

                    case 6: //Mostrar interfaz gráfica
                        launch();
                        break;
                    case 7: //Salir
                        System.out.println("Saliendo . . .");
                        break;

                    default:
                        System.out.println("Opción incorrecta");
                        break;
                }
            }
        } catch (InputMismatchException | InterruptedException e) {
            System.out.println("Tipo de dato erroneo");
        }



    }


    public void start(Stage stage) throws IOException {
       FXMLLoader loader=new FXMLLoader();
       loader.setLocation(R.getUI("login.fxml"));

       LoginController controller=new LoginController();
       loader.setController(controller);
       VBox vBox=loader.load();


       controller.setGestorUsuarios(gestorUsuarios);

       Scene scene=new Scene(vBox);
       stage.setScene(scene);
       stage.show();
       stage.setTitle("Inicio de sesion");
    }
    public void init() throws Exception{
        super.init();
    }

    public void stop() throws Exception{
        super.stop();
    }

    public static void menuPrincipal(){
        System.out.println("---Menú principal---");
        System.out.println("\t1-Dar de alta a un usuario");
        System.out.println("\t2-Mostrar usuarios en función del tipo");
        System.out.println("\t3-Eliminar usuario");
        System.out.println("\t4-Establecer IRPF");
        System.out.println("\t5-Generar nomina");
        System.out.println("\t6-Interfaz gráfica");
        System.out.println("\t7-Salir");
        System.out.print("\tElija una opción:");
    }

    public static void tipoUsuarioMenu(){
        System.out.println("Tipos de usuario");
        System.out.println("\t0-Estudiante");
        System.out.println("\t1-Profesor");
        System.out.print("\tElija una opción:");
    }


}
