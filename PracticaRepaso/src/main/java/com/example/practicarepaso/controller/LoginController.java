package com.example.practicarepaso.controller;

import com.example.practicarepaso.modelos.GestionUsuarios;
import com.example.practicarepaso.modelos.GestorUsuarios;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    public TextField tfEmail;
    public TextField tfPass;

    public Button btValidar;
    public Button btSalir;

    private GestionUsuarios gestionUsuarios;
    public void setGestionUsuarios(GestionUsuarios gestionUsuarios){
        this.gestionUsuarios=gestionUsuarios;
    }
    @FXML
    public void validar(Event event) {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        String email = tfEmail.getText();
        String pass = tfPass.getText();
        if (email.isEmpty() || pass.isEmpty()) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Iniciar sesión");
            error.setContentText("Debe introducir todos los datos");
            error.showAndWait();
        } else {
            if (gestorUsuarios.validarUsuario(email, pass)) {
                Alert correcto = new Alert(Alert.AlertType.INFORMATION);
                correcto.setTitle("Iniciar sesión");
                correcto.setContentText("Los datos introducidos son correctos");
                correcto.showAndWait();
            } else {
                Alert incorrecto = new Alert(Alert.AlertType.INFORMATION);
                incorrecto.setTitle("Iniciar sesión");
                incorrecto.setContentText("Los datos introducidos no son correctos");
                incorrecto.showAndWait();
            }
        }


    }

    public void salir(Event event) {
        Stage cerrar = (Stage) tfEmail.getScene().getWindow();
        cerrar.close();
    }

}
