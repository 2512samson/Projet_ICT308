package com.example.projet_ict308.controllers;

import com.example.projet_ict308.tools.Notification;
import com.example.projet_ict308.tools.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import com.example.projet_ict308.entities.Question;
import com.example.projet_ict308.entities.QuestionDatabase;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class JeuxControllers {


    @FXML
    private MediaView media;
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
    @FXML
    private ChoiceBox<String> niveaujeuxcb;
    @FXML
    private MediaView mediaView;  // Ajout du MediaView

    private MediaPlayer mediaPlayer;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private QuestionDatabase questionDatabase;
    private Question currentQuestion;
    private int correctAnswers;
    private boolean gameStarted = false;

    private final String[] niveau = {"Facile", "Moyen", "Difficile"};

    @FXML
    public void initialize() {
        executorService.submit(() -> playAudio("/musique1.mp3"));
        niveaujeuxcb.getItems().addAll(niveau);
        questionDatabase = new QuestionDatabase();
        correctAnswers = 0;
        disableAnswerButtons();
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
    }

    private void loadNewQuestion() {
        if (!gameStarted) {
            return;
        }

        String selectedNiveau = niveaujeuxcb.getValue();
        if (selectedNiveau == null) {
            Notification.NotifError("Erreur", "Veuillez d'abord sélectionner un niveau.");
            return;
        }

        try {
            currentQuestion = questionDatabase.getRandomQuestionByNiveau(selectedNiveau);
            question.setText(currentQuestion.getLibelle());

            List<String> answers = new ArrayList<>();
            answers.add(currentQuestion.getReponse());
            answers.add(currentQuestion.getFaurepo1());
            answers.add(currentQuestion.getFaurepo2());
            answers.add(currentQuestion.getFaurepo3());
            answers.add(currentQuestion.getNiveau());
            Collections.shuffle(answers);

            Reponse1.setText(answers.get(0));
            Reponse2.setText(answers.get(1));
            Reponse3.setText(answers.get(2));
            Reponse4.setText(answers.get(3));

        } catch (IllegalStateException e) {
            question.setText("No questions available for the selected level: " + e.getMessage());
            disableAnswerButtons();
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
        disableAnswerButtons();
        stopAudio();

        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
        scheduledExecutor.schedule(() -> {
            playVideo("/video.mp4");
            scheduledExecutor.schedule(this::stopVideo, 20, TimeUnit.SECONDS);
        }, 0, TimeUnit.SECONDS);
    }

    @FXML
    private void Onbreack(ActionEvent event) {
        endGame();
        try {
            Notification.NotifSuccess("Succes", "Vous pouvez mieux faire !!!");
            Outils.load(event, "Expert", "/Pages/Jeux.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void Onplaying(ActionEvent event) {
        if (niveaujeuxcb.getValue() == null) {
            loginMessageLabel.setText("Veuillez d'abord sélectionner un niveau.");
            return;
        }
        gameStarted = true;
        correctAnswers = 0;
        enableAnswerButtons();
        stopVideo();

        // Jouer le son de début de jeu
        executorService.submit(() -> playAudio("/musique1.mp3"));

        loadNewQuestion();
    }

    private void playAudio(String audioFilePath) {
        try {
            URL resource = getClass().getResource(audioFilePath);
            if (resource == null) {
                throw new IllegalArgumentException("Fichier audio introuvable: " + audioFilePath);
            }
            Media audioMedia = new Media(resource.toExternalForm());
            mediaPlayer = new MediaPlayer(audioMedia);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la lecture du fichier audio : " + e.getMessage());
        }
    }

    private void stopAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    private void playVideo(String videoFilePath) {
        try {
            URL resource = getClass().getResource(videoFilePath);
            if (resource == null) {
                throw new IllegalArgumentException("Fichier vidéo introuvable: " + videoFilePath);
            }
            Media videoMedia = new Media(resource.toExternalForm());
            mediaPlayer = new MediaPlayer(videoMedia);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
            // Utilisation du bon MediaView (ici, media)
            media.setMediaPlayer(mediaPlayer);

            mediaPlayer.setOnReady(() -> {
                mediaPlayer.play();
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la lecture du fichier vidéo : " + e.getMessage());
        }
    }
    private void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }



    private void disableAnswerButtons() {
        Reponse1.setDisable(true);
        Reponse2.setDisable(true);
        Reponse3.setDisable(true);
        Reponse4.setDisable(true);
    }

    private void enableAnswerButtons() {
        Reponse1.setDisable(false);
        Reponse2.setDisable(false);
        Reponse3.setDisable(false);
        Reponse4.setDisable(false);
    }

    @FXML
    public void Onbreack1(ActionEvent event) {
        endGame();
        stopVideo();
        try {
            Notification.NotifSuccess("Succes", "Au revoir !!!");
            Outils.load(event, "Expert", "/Pages/Login.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}