package com.example.projet_ict308.controllers;

import com.example.projet_ict308.tools.Notification;
import com.example.projet_ict308.tools.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class AdminControllers {

    @FXML
    private Button Addquestion;

    @FXML
    private Button Play;

    @FXML
    private Label loginMessageLabel;

    @FXML
    void Onadd(ActionEvent event) {
        try {
            Notification.NotifSuccess("Succes","Vous préférez plutôt ajouter des questions, merci pour cette action.");
            Outils.load(event,"Expert" ,"/Pages/Addquestion.fxml");
        }catch ( IOException e){
            throw  new RuntimeException(e);
        }
    }

    @FXML
    void Onplay(ActionEvent event) {
        try {
            Notification.NotifSuccess("Succes","Détendez-vous en prenant du plaisir à jouer.");
            Outils.load(event,"Trivial Game" ,"/Pages/Jeux.fxml");
        }catch ( IOException e){
            throw  new RuntimeException(e);
        }
    }

    public void Onbreak(ActionEvent event) {
        try {
            Notification.NotifSuccess("Succes","Au-revoir !");
            Outils.load(event,"Trivial Game" ,"/Pages/Login.fxml");
        }catch ( IOException e){
            throw  new RuntimeException(e);
        }
    }

    public void Onchange(ActionEvent event) {
        try {
            Notification.NotifSuccess("Succes","Faire le CRUD sur les questions !");
            Outils.load(event,"CRUD" ,"/Pages/Crudadmin.fxml");
        }catch ( IOException e){
            throw  new RuntimeException(e);
        }
    }
}
