module com.example.projet_ict308 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires TrayTester;


    opens com.example.projet_ict308 to javafx.fxml;
    exports com.example.projet_ict308;
    //Exposition des controllers
    opens com.example.projet_ict308.controllers to javafx.fxml;
    exports com.example.projet_ict308.controllers;
    //Exposition des entites
    exports com.example.projet_ict308.entities;
    opens com.example.projet_ict308.entities to javafx.fxml;
    //Exposition des dao
    exports com.example.projet_ict308.dao;
    opens com.example.projet_ict308.dao to javafx.fxml;

}