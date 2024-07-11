package com.example.projet_ict308.entities;

public class Question {
    private int id;
    private String libelle;
    private String reponse;
    private String faurepo1;
    private String faurepo2;
    private String faurepo3;
    private String niveau;

    public Question(){};
    public Question(int id, String libelle, String reponse, String faurepo1, String faurepo2, String faurepo3,String niveau) {
        this.id = id;
        this.libelle = libelle;
        this.reponse = reponse;
        this.faurepo1 = faurepo1;
        this.faurepo2 = faurepo2;
        this.faurepo3 = faurepo3;
        this.niveau = niveau;
    }


    // Getters and setters for all fields


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getFaurepo1() {
        return faurepo1;
    }

    public void setFaurepo1(String faurepo1) {
        this.faurepo1 = faurepo1;
    }

    public String getFaurepo2() {
        return faurepo2;
    }

    public void setFaurepo2(String faurepo2) {
        this.faurepo2 = faurepo2;
    }

    public String getFaurepo3() {
        return faurepo3;
    }

    public void setFaurepo3(String faurepo3) {
        this.faurepo3 = faurepo3;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getNiveau() {
        return  niveau;
    }
}
