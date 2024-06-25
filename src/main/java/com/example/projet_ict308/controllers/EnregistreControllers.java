package com.example.projet_ict308.controllers;

import com.example.projet_ict308.dao.DBConnection;
import com.example.projet_ict308.tools.Notification;
import com.example.projet_ict308.tools.Outils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EnregistreControllers implements Initializable {
    private DBConnection db = new DBConnection();
    @FXML
    private TextField agetfd;

    @FXML
    private TextField emailtfd;

    @FXML
    private Button enregistrementannuler;

    @FXML
    private Button enregistrer;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField nomtfd;

    @FXML
    private PasswordField passwordtfd;

    @FXML
    private PasswordField passwordtfd1;

    @FXML
    private ChoiceBox<String> sexecb;

    private final String[] sexe = {"Masculin", "Feminin"};

    @FXML
    void getsignin(ActionEvent event) {
        String nom = nomtfd.getText().trim();
        String sexe = sexecb.getValue();
        String ageStr = agetfd.getText().trim();
        String email = emailtfd.getText().trim();
        String password = passwordtfd.getText();
        String password2 = passwordtfd1.getText();

        // Validation des champs
        if (nom.isEmpty()) {
            Notification.NotifError("Erreur", "Le nom est obligatoire !");
            return;
        }

        if (sexe == null) {
            Notification.NotifError("Erreur", "Le choix du sexe est obligatoire !");
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
            if (age <= 0) {
                Notification.NotifError("Erreur", "L'âge doit être un nombre positif !");
                return;
            }
        } catch (NumberFormatException e) {
            Notification.NotifError("Erreur", "L'âge doit être un nombre !");
            return;
        }

        if (email.isEmpty() || !email.contains("@")) {
            Notification.NotifError("Erreur", "L'adresse email n'est pas valide !");
            return;
        }

        // Validation du mot de passe
        if (!isPasswordValid(password)) {
            Notification.NotifError("Erreur", "Le mot de passe doit contenir au moins 8 caractères, une lettre majuscule, une lettre minuscule et un caractère spécial !");
            return;
        }

        if (!password.equals(password2)) {
            Notification.NotifError("Erreur", "Les mots de passe ne correspondent pas !");
            return;
        }

        // Toutes les validations sont passées, on peut procéder à l'enregistrement
        String sql = "INSERT INTO joueur (nom, sexe, age, email, password) VALUES (?, ?, ?, ?, ?)";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, nom);
            db.getPstm().setString(2, sexe);
            db.getPstm().setInt(3, age);
            db.getPstm().setString(4, email);
            db.getPstm().setString(5, password);
            int ok = db.executeMaj();
            db.closeConnection();
            clearFields();
            Notification.NotifSuccess("Succès", "Joueur bien enregistré !");
            Outils.load(event, "Login", "/Pages/Login.fxml");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            Notification.NotifError("Erreur", "Erreur lors de l'enregistrement du joueur !");
        }
    }

    void clearFields() {
        nomtfd.setText("");
        sexecb.getSelectionModel().clearSelection();
        agetfd.setText("");
        emailtfd.setText("");
        passwordtfd.setText("");
        passwordtfd1.setText("");
    }

    boolean isPasswordValid(String password) {
        // Vérifie si le mot de passe respecte les critères : au moins 8 caractères, une majuscule, une minuscule, un caractère spécial
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sexecb.getItems().addAll(sexe);

    }

    public void getCancul(ActionEvent event) {
        try {
            Notification.NotifSuccess("Succes","Au-revoi !");
            Outils.load(event,"Trivial Game" ,"/Pages/Login.fxml");
        }catch ( IOException e){
            throw  new RuntimeException(e);
        }
    }

}
