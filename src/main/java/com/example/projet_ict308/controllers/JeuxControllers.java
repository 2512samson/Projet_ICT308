package com.example.projet_ict308.controllers;

import com.example.projet_ict308.tools.Notification;
import com.example.projet_ict308.tools.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import com.example.projet_ict308.entities.Question;
import com.example.projet_ict308.entities.QuestionDatabase;
import com.example.projet_ict308.entities.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JeuxControllers {

    @FXML
    private TextArea question;
    @FXML
    private Button Reponse1;
    @FXML
    private Button Reponse2;
    @FXML
    private Button Reponse3;
    @FXML
    private Button Reponse4;
    @FXML
    private Label loginMessageLabel;

    private QuestionDatabase questionDatabase;
    private Question currentQuestion;
    private int correctAnswers;

    @FXML
    public void initialize() {
        questionDatabase = new QuestionDatabase();
        correctAnswers = 0;
        loadNewQuestion();
    }

    private void loadNewQuestion() {

        try {
            // code pour afficher la question
            currentQuestion = questionDatabase.getRandomQuestion();
            question.setText(currentQuestion.getLibelle());
            List<String> answers = new ArrayList<>();
            answers.add(currentQuestion.getReponse());
            answers.add(currentQuestion.getFaurepo1());
            answers.add(currentQuestion.getFaurepo2());
            answers.add(currentQuestion.getFaurepo3());

            Collections.shuffle(answers);

            Reponse1.setText(answers.get(0));
            Reponse2.setText(answers.get(1));
            Reponse3.setText(answers.get(2));
            Reponse4.setText(answers.get(3));
        } catch (IllegalStateException e) {
            // gérer le cas où la liste des questions est vide
            System.out.println("No questions available: " + e.getMessage());
            // ou afficher un message d'erreur à l'utilisateur
        }


    }

    @FXML
    private void Reponse1() {
        checkAnswer(Reponse1.getText());
    }

    @FXML
    private void Reponse2() {
        checkAnswer(Reponse2.getText());
    }

    @FXML
    private void Reponse3() {
        checkAnswer(Reponse3.getText());
    }

    @FXML
    private void Reponse4() {
        checkAnswer(Reponse4.getText());
    }

    private void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(currentQuestion.getReponse())) {
            correctAnswers++;
            loadNewQuestion();
        } else {
            endGame();
        }
    }

    private void endGame() {
        question.setText("Game Over! Correct Answers: " + correctAnswers);
        Reponse1.setDisable(true);
        Reponse2.setDisable(true);
        Reponse3.setDisable(true);
        Reponse4.setDisable(true);
    }

    @FXML
    private void Onbreack( ActionEvent event ) {
        endGame();
        try {
            Notification.NotifSuccess("Succes","Vous pouvez mieux faire !!!");
            Outils.load(event,"Expert" ,"/Pages/Login.fxml");
        }catch ( IOException e){
            throw  new RuntimeException(e);
        }
    }
}
