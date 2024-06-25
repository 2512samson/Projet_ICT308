package com.example.projet_ict308.dao;
import java.sql.*;


public class DBConnection {
    //Pour la connexion
    private  Connection cnx;
    //Pour les requetes preparees
    private PreparedStatement pstm;
    //Pour les requetes de consultation
    private ResultSet rs;
    //Pour les requetes de mise a jour (INSERT INTO, UPDATE, DELETE)
    private int ok;

    //Methode d'ouverture de la connexion
    public Connection getConnection(){
        //Parametre de connexion
//        String host = "localhost";
//        String database = "ict308";
        String url = "jdbc:mysql://localhost:3306/ict308";
        String user = "root";
        String password = "root";
        try {
            //Chargement du pilote
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Ouverture de la connexiomn
            cnx = DriverManager.getConnection(url,user,password);
            //System.out.println("Connexion reussie !!!");

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return cnx;
    }
    public  void initPrepar(String sql){
        try {
            getConnection();
            pstm = cnx.prepareStatement(sql);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public ResultSet executeQuery(){
        rs = null;
        try {
            rs = pstm.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  rs;
    }

    public  int executeMaj(){
        try {
            ok = pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  ok;
    }
    public  void  closeConnection(){
        try {
            if (cnx != null)
                cnx.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  PreparedStatement getPstm(){
        return  pstm;
    }

    public ResultSet executeSelect() {
        rs = null;
        try {
            rs = pstm.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  rs;
    }
}






