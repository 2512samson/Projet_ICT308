package com.example.projet_ict308.controllers;

import com.example.projet_ict308.dao.DBConnection;
import com.example.projet_ict308.entities.Question;
import com.example.projet_ict308.tools.Notification;
import com.example.projet_ict308.tools.Outils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CrudadminControllers implements Initializable {

    @FXML
    private Button arreter;

    @FXML
    private Button effacerbtn;

    @FXML
    private Button enregistrerbtn;

    @FXML
    private TextArea faussereponse1;

    @FXML
    private TextArea faussereponse2;

    @FXML
    private TextArea faussereponse3;

    @FXML
    private TableColumn<Question, Integer> idcolum;

    @FXML
    private Button modifierbtn;

    @FXML
    private TextArea question1;

    @FXML
    private TableColumn<Question, String> questioncolum;

    @FXML
    private TableView<Question> questiontb;

    @FXML
    private TextArea reponse;

    @FXML
    private Button supprimerbtn;

    private int idQuestion;

    @FXML
    private ChoiceBox<String> niveauadmincb;

    private final String[] niveau = {"Facile", "Moyen", "Difficile"};
    private DBConnection db = new DBConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        niveauadmincb.getItems().addAll(niveau);
        loadTable();
    }

    public ObservableList<Question> getQuestions() {
        ObservableList<Question> questions = FXCollections.observableArrayList();
        String sql = "SELECT * FROM question ORDER BY id ASC";
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeSelect();
            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setLibelle(rs.getString("libelle"));
                question.setReponse(rs.getString("reponse"));
                question.setFaurepo1(rs.getString("faurepo1"));
                question.setFaurepo2(rs.getString("faurepo2"));
                question.setFaurepo3(rs.getString("faurepo3"));
                question.setNiveau(rs.getString("niveau"));
                questions.add(question);
            }
            db.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

    @FXML
    void clear(ActionEvent event) {
        clearFields();
    }

    @FXML
    void delete(ActionEvent event) {
        String sql = "DELETE FROM question WHERE id = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, idQuestion);
            int ok = db.executeMaj();
            db.closeConnection();
            loadTable();
            clearFields();
            enregistrerbtn.setDisable(false);
            Notification.NotifSuccess("Success", "Question deleted successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getCancul(ActionEvent event) {
        try {
            Notification.NotifSuccess("Success", "Goodbye!");
            Outils.load(event, "Trivial Game", "/Pages/Admin.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void getDate(MouseEvent event) {
        Question question = questiontb.getSelectionModel().getSelectedItem();
        idQuestion = question.getId();
        question1.setText(question.getLibelle());
        reponse.setText(question.getReponse());
        faussereponse1.setText(question.getFaurepo1());
        faussereponse2.setText(question.getFaurepo2());
        faussereponse3.setText(question.getFaurepo3());
        niveauadmincb.setValue(question.getNiveau());
        enregistrerbtn.setDisable(true);
    }

    @FXML
    void save(ActionEvent event) {
        String sql = "INSERT INTO question VALUES (NULL,?,?,?,?,?,?)";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, question1.getText());
            db.getPstm().setString(2, reponse.getText());
            db.getPstm().setString(3, faussereponse1.getText());
            db.getPstm().setString(4, faussereponse2.getText());
            db.getPstm().setString(5, faussereponse3.getText());
            db.getPstm().setString(6, niveauadmincb.getValue());
            int ok = db.executeMaj();
            db.closeConnection();
            loadTable();
            clearFields();
            Notification.NotifSuccess("Success", "Question saved successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void update(ActionEvent event) {
        String sql = "UPDATE question SET libelle = ?, reponse = ?, faurepo1 = ?, faurepo2 = ?, faurepo3 = ?, niveau = ? WHERE id = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, question1.getText());
            db.getPstm().setString(2, reponse.getText());
            db.getPstm().setString(3, faussereponse1.getText());
            db.getPstm().setString(4, faussereponse2.getText());
            db.getPstm().setString(5, faussereponse3.getText());
            db.getPstm().setString(6, niveauadmincb.getValue());
            db.getPstm().setInt(7, idQuestion);
            int ok = db.executeMaj();
            db.closeConnection();
            loadTable();
            clearFields();
            enregistrerbtn.setDisable(false);
            Notification.NotifSuccess("Success", "Question updated successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void clearFields() {
        question1.setText("");
        reponse.setText("");
        faussereponse1.setText("");
        faussereponse2.setText("");
        faussereponse3.setText("");
        niveauadmincb.setValue(null);
    }

    public void loadTable() {
        ObservableList<Question> liste = getQuestions();
        questiontb.setItems(liste);
        idcolum.setCellValueFactory(new PropertyValueFactory<>("id"));
        questioncolum.setCellValueFactory(new PropertyValueFactory<>("libelle"));
    }
}
