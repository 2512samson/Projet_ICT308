package com.example.projet_ict308.controllers;

import com.example.projet_ict308.dao.IUser;
import com.example.projet_ict308.dao.UserImpl;
import com.example.projet_ict308.entities.User;
import com.example.projet_ict308.tools.Notification;
import com.example.projet_ict308.tools.Outils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginControllers extends Application {

    @FXML
    private Button connexion;

    @FXML
    private TextField emailtfd;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private PasswordField passwordtfd;

    IUser userDao = new UserImpl();

    @FXML
    void getLogin(ActionEvent event) {
        String email = emailtfd.getText();
        String password = passwordtfd.getText();
        if(email.equals("") || password.equals("")){
            Notification.NotifError("Erreur", "Tous les champs sont obligatoires");
        } else {
            try {
                if (!userDao.userExists(email)) {
                    Notification.NotifError("Erreur", "Utilisateur non trouvé !");
                } else {
                    User user = userDao.seConnecter(email, password);
                    if (user == null) {
                        Notification.NotifError("Erreur", "Email et/ou mot de passe incorrects !");
                    } else {
                        Notification.NotifSuccess("Succes", "Connexion reussie !!");

                        // Vérifier si le mot de passe contient un caractère spécial
                        if (containsSpecialCharacter(password)) {
                            Outils.load(event, "Jeux", "/Pages/Jeux.fxml");
                        } else {
                            Outils.load(event, "Administrateur", "/Pages/Admin.fxml");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean containsSpecialCharacter(String s) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Lancer l'application
    }

    public void getSignIn(ActionEvent event) {
        try {
            Notification.NotifSuccess("Succes","Enregistrez-vous !");
            Outils.load(event,"Enregistrement" ,"/Pages/Enregistre.fxml");
        }catch ( IOException e){
            throw  new RuntimeException(e);
        }
    }

    public void getSignup(ActionEvent event) {
        Platform.exit();
    }
}
