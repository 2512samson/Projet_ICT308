package com.example.projet_ict308.controllers;

import com.example.projet_ict308.dao.DBConnection;
import com.example.projet_ict308.tools.Notification;
import com.example.projet_ict308.tools.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddquestionControllers {

    private DBConnection db = new DBConnection();
    @FXML
    private TextArea question;

    @FXML
    private TextArea reponsejust;

    @FXML
    private TextArea reponsefausse1;

    @FXML
    private TextArea reponsefausse2;

    @FXML
    private TextArea reponsefausse3;

    @FXML
    private Button Addquestion;

    @FXML
    private Label loginMessageLabel;

    @FXML
    void Onclick(ActionEvent event) {
        try {
            Notification.NotifSuccess("Succes","Au revoir  !!!");
            Outils.load(event,"Expert" ,"/Pages/Admin.fxml");
        }catch ( IOException e){
            throw  new RuntimeException(e);
        }
    }

    // Méthode appelée lors de l'appui sur le bouton Ajouter
    @FXML
    private void Onsubmit() {
        String questionText = question.getText();
        String reponseJusteText = reponsejust.getText();
        String reponseFausse1Text = reponsefausse1.getText();
        String reponseFausse2Text = reponsefausse2.getText();
        String reponseFausse3Text = reponsefausse3.getText();

        if (questionText.isEmpty() || reponseJusteText.isEmpty() || reponseFausse1Text.isEmpty() || reponseFausse2Text.isEmpty() || reponseFausse3Text.isEmpty()) {
            loginMessageLabel.setText("Veuillez remplir tous les champs.");
            return;
        }

        try {
            addQuestionToDatabase(questionText, reponseJusteText, reponseFausse1Text, reponseFausse2Text, reponseFausse3Text);
            loginMessageLabel.setText("Question ajoutée avec succès !");
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            loginMessageLabel.setText("Erreur lors de l'ajout de la question.");
        }
    }

    private void addQuestionToDatabase(String question, String reponse, String faurepo1, String faurepo2, String faurepo3) throws SQLException {

        String sql = "INSERT INTO question (libelle, reponse, faurepo1, faurepo2, faurepo3) VALUES (?, ?, ?, ?, ?)";

        try {
             db.initPrepar(sql);
             db.getPstm().setString(1, question);
            db.getPstm().setString(2, reponse);
            db.getPstm().setString(3, faurepo1);
            db.getPstm().setString(4, faurepo2);
            db.getPstm().setString(5, faurepo3);

            db.getPstm().executeUpdate();
            Notification.NotifSuccess("Succes","La question a été ajouter avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        question.clear();
        reponsejust.clear();
        reponsefausse1.clear();
        reponsefausse2.clear();
        reponsefausse3.clear();
    }
}
