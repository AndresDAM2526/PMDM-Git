package com.example.practicarepaso;

import javafx.application.Application;
import modelos.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Launcher {


    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        GestorUsuarios gestorUsuarios=new GestorUsuarios();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            int opcionUsuario=-1;
            int tipoUsuario;
            while(opcionUsuario!=5){
                menuPrincipal();
                opcionUsuario=teclado.nextInt();
                switch (opcionUsuario){
                    case 1:
                        int idUsuario;
                        String nombreUsuario;
                        String email;
                        String pass;
                        String curso;
                        System.out.println("---Dar de alta al usuario---");
                        tipoUsuarioMenu();
                        tipoUsuario=teclado.nextInt();
                        switch (tipoUsuario){
                            case 0:
                                System.out.println("Introduzca el ID del usuario:");
                                idUsuario=teclado.nextInt();
                                teclado.nextLine();
                                System.out.println("Introduzca el nombre:");
                                nombreUsuario=teclado.nextLine();
                                System.out.println("Introduzca el email:");
                                email=teclado.nextLine();
                                System.out.println("Introduzca la contraseña:");
                                pass=teclado.nextLine();
                                System.out.println("Introduzca el curso:");
                                curso=teclado.nextLine();
                                Estudiante est=new Estudiante(idUsuario,nombreUsuario,email,pass,curso);
                                gestorUsuarios.agregarUsuario(est);
                                break;
                            case 1:
                                String especialidad="";
                                String fechaComienzo="";
                                LocalDate fechaComienzoLd;
                                int irpf;
                                String cargoStr;
                                Cargo cargo;
                                System.out.println("Introduzca el ID del usuario:");
                                idUsuario=teclado.nextInt();
                                teclado.nextLine();
                                System.out.println("Introduzca el nombre:");
                                nombreUsuario=teclado.nextLine();
                                System.out.println("Introduzca el email:");
                                email=teclado.nextLine();
                                System.out.println("Introduzca la contraseña:");
                                pass=teclado.nextLine();
                                System.out.println("Introduzca la especialidad:");
                                especialidad=teclado.nextLine();
                                System.out.println("Introduzca su fecha de alta");
                                fechaComienzoLd=LocalDate.parse(fechaComienzo,formatter);
                                System.out.println("Intoduzca el IRPF:");
                                irpf=teclado.nextInt();
                                teclado.nextLine();
                                System.out.println("Introduzca su cargo(Director,Secretario,Profesor):");
                                cargoStr=teclado.nextLine();
                                cargo=Cargo.valueOf(cargoStr);
                                Profesor pf=new Profesor(idUsuario,nombreUsuario,email,pass,especialidad,fechaComienzoLd,irpf,cargo);
                                break;
                            default:
                                System.out.println("Opción incorrecta");
                                break;
                        }
                        break;
                    case 2:
                        try {
                            System.out.println("---Listar usuarios---");
                            tipoUsuarioMenu();
                            int tipoUsuarioListar=teclado.nextInt();
                            switch (tipoUsuarioListar){
                                case 0:
                                    gestorUsuarios.listarUsuario(0);
                                    break;
                                case 1:
                                    gestorUsuarios.listarUsuario(1);
                                    break;
                                default:
                                    System.out.println("Opción incorrecta");
                                    break;
                            }
                        }catch (InputMismatchException e){
                            System.out.println("Tipo de dato introducido incorrecto");
                        }

                    case 3:
                        try {
                            System.out.println("---Eliminar usuario---");
                            System.out.println("Introduzca el ID del usuario que quiere eliminar");
                            int idUsuarioEliminar=teclado.nextInt();
                            gestorUsuarios.eliminarUsuaios(idUsuarioEliminar);

                        }catch (InputMismatchException e){
                            System.out.println("Tipo de dato introducido incorrecto");
                        }
                    case 4:
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
                    case 5:
                        System.out.println("Saliendo . . .");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Tipo de dato erroneo");
        }


        //Application.launch(HelloApplication.class, args)
    }

    public static void menuPrincipal(){
        System.out.println("---Menú principal---");
        System.out.println("1-Dar de alta a un usuario");
        System.out.println("2-Mostrar usuarios en función del tipo");
        System.out.println("3-Eliminar usuario");
        System.out.println("4-Establecer IRPF");
        System.out.println("5-Salir");
        System.out.print("Elija una opción:");
    }

    public static void tipoUsuarioMenu(){
        System.out.println("Tipos de usuario");
        System.out.println("0-Estudiante");
        System.out.println("1-Profesor");
        System.out.println("Elija una opción:");
    }


}
