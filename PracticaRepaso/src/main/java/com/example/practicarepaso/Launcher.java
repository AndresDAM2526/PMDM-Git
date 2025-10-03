package com.example.practicarepaso;

import javafx.application.Application;
import modelos.GestorUsuarios;
import modelos.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Launcher {


    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        GestorUsuarios gestorUsuarios=new GestorUsuarios();
        try {
            int opcionUsuario=-1;
            while(opcionUsuario!=5){
                menuPrincipal();
                opcionUsuario=teclado.nextInt();
                switch (opcionUsuario){
                    case 1:
                        System.out.println();
                        int idUsuario;
                        String nombreUsuario;
                        String email;
                        String pass;
                        System.out.println("Introduzca el ID del usuario");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Tipo de dato erroneo");
        }


        //Application.launch(HelloApplication.class, args)
    }

    public static void menuPrincipal(){
        System.out.println("1-Dar de alta a un usuario");
        System.out.println("2-Mostrar usuarios en función del tipo");
        System.out.println("3-Eliminar usuario");
        System.out.println("4-Establecer IRPF");
        System.out.println("5-Salir");
        System.out.print("Elija una opción:");
    }

    public static void tipoUsuarioMenu(){
        System.out.println("Tipos de usuario");
        System.out.println("a-Estudiante");
        System.out.println("b-Profesor");
        System.out.println("Elija una opción");
    }


}
