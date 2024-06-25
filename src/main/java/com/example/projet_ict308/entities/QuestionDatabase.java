package com.example.projet_ict308.entities;

import com.example.projet_ict308.dao.DBConnection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionDatabase {
    private List<Question> questions;
    private Random random;
    private  DBConnection db = new DBConnection();
    public QuestionDatabase() {
        questions = new ArrayList<>();
        random = new Random();
        loadQuestions();
    }

    private void loadQuestions() {
        String sql = "SELECT * FROM question" ;
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
                questions.add(new Question(id, libelle, reponse, faurepo1, faurepo2, faurepo3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("La liste des question est vide !");
        }
        int index = random.nextInt(questions.size());
        return questions.get(index);
    }

}
