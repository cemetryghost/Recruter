package com.example.kval_ex;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

// Класс для перехода на соответствующие окна

public class RoleController {

    @FXML
    private Button recruterButton, userButton;

    @FXML
    void RecruterButton() throws IOException {
        Stage stageToClose = (Stage) recruterButton.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("auth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("auth-view");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void UserButton() throws  IOException{
        Stage stageToClose = (Stage) userButton.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-application-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("user-application-view");
        stage.setScene(scene);
        stage.show();
    }
}
