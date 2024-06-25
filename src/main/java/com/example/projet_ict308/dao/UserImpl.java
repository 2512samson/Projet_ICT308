package com.example.projet_ict308.dao;

import com.example.projet_ict308.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl implements IUser {

    private  DBConnection db = new DBConnection();
    private ResultSet rs;

    @Override
    public User seConnecter(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM joueur WHERE email = ? AND password =  ?" ;
        try {
            //Initialisation de la requete
            db.initPrepar(sql);
            //Passage de valeurs
            db.getPstm().setString(1,email);
            db.getPstm().setString(2,password);
            //Execution de la requete
            rs = db.executeSelect();
            if(rs.next()) {
                user = new User();
                user.setIdU(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

            }
            //Fermeture de la requete
                db.closeConnection();

        }catch (Exception e){
            e.printStackTrace();
        }
        return  user;
    }

    public boolean userExists(String email) {
        boolean exists = false;
        String sql = "SELECT * FROM joueur WHERE email = ?";
        try  {
            db.initPrepar(sql);
            db.getPstm().setString(1, email);
            ResultSet resultSet = db.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }


}
