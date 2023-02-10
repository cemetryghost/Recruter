package com.example.kval_ex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Стартовый класс приложения

public class RoleView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RoleView.class.getResource("role-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("role-view");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}