package com.example.kval_ex;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;

// Класс авторизации рекрутера
public class AuthView{

    @FXML private Button enterButton;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;

    String login = "recruter", password = "1";

    // Метод проверки логина и пароля

    @FXML
    void EnterButton() throws IOException {
        if (!passwordField.getText().equals(password) || !loginField.getText().equals(login)) {
            JOptionPane.showMessageDialog(null, "Неверный логин или пароль! Проверьте корректность заполнения полей!");
        } else {
            Stage stageToClose = (Stage) enterButton.getScene().getWindow();
            stageToClose.close();

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recruter-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("recruter-view");
            stage.setScene(scene);
            stage.show();
        }
    }
}

