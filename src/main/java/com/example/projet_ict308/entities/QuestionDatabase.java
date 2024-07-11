package com.example.projet_ict308.entities;

import com.example.projet_ict308.dao.DBConnection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionDatabase {
    private List<Question> questions;
    private Random random;
    private DBConnection db;

    public QuestionDatabase() {
        questions = new ArrayList<>();
        random = new Random();
        db = new DBConnection();
    }

    private void loadQuestions() {
        String sql = "SELECT * FROM question";
        try {
            db.initPrepar(sql);
            ResultSet rs = db.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String libelle = rs.getString("libelle");
                String reponse = rs.getString("reponse");
                String faurepo1 = rs.getString("faurepo1");
                String faurepo2 = rs.getString("faurepo2");
                String faurepo3 = rs.getString("faurepo3");
                String niveau = rs.getString("niveau");
                questions.add(new Question(id, libelle, reponse, faurepo1, faurepo2, faurepo3,niveau ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Question getRandomQuestionByNiveau(String niveau) {
        List<Question> questionsByNiveau = new ArrayList<>();
        String sql = "SELECT * FROM question WHERE niveau = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, niveau);
            ResultSet rs = db.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String libelle = rs.getString("libelle");
                String reponse = rs.getString("reponse");
                String faurepo1 = rs.getString("faurepo1");
                String faurepo2 = rs.getString("faurepo2");
                String faurepo3 = rs.getString("faurepo3");
                String niveaux = rs.getString("niveau");
                questionsByNiveau.add(new Question(id, libelle, reponse, faurepo1, faurepo2, faurepo3, niveaux));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (questionsByNiveau.isEmpty()) {
            throw new IllegalStateException("No questions available for the selected level!");
        }

        return questionsByNiveau.get(random.nextInt(questionsByNiveau.size()));
    }
}
