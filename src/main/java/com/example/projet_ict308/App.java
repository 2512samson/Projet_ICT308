package com.example.projet_ict308;

import com.example.projet_ict308.dao.DBConnection;
import com.example.projet_ict308.dao.IUser;
import com.example.projet_ict308.dao.UserImpl;
import com.example.projet_ict308.entities.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/Pages/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 928, 612);
        stage.setTitle("Page de connexion!");
        stage.setScene(scene);
        stage.show();

//        Parent parent=FXMLLoader.load(getClass().getResource("/Pages/Login.fxml"));
//        Scene scene = new Scene(parent);
//        stage.setTitle("Page de connexion !");
//        stage.setScene(scene);
//        stage.show();
    }


    public static void main(String[] args) {

        DBConnection db = new DBConnection();
        db.getConnection();

//        IUser dao = new UserImpl();
//        User user =  dao.seConnecter("sam@gmail.com","HDS2512SAM");
//        if(user != null)
//            System.out.println("Bien joue");
//        else
//            System.out.println("Peut miex faire");
        launch();
    }


}
