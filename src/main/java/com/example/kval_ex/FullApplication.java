package com.example.kval_ex;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Класс отображения полной заявки пользователя в роли рекрутера

public class FullApplication implements Initializable {

    @FXML private Label nameText, dateText, numberText, salaryText, specialityText, experienceText, vacancyText, cityText, educationText;
    @FXML private TextArea skillsText;
    @FXML private Button backButton;

    // Метод отображения данных из файла с заявками

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Читаем пока не конец стркоки с записью

        String text = "";
        String line;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("Recruter-master\\files\\application.txt"))){
            while((line = bufferedReader.readLine()) != null){
                text += line + "\r\n";
            }

            // Создаем массив для отображения элементов по индексу

            String[] array = text.split("\r\n");
            String[] newArray = array[RecruterView.iindex].split(":");

            nameText.setText(newArray[0]);
            dateText.setText(newArray[1]);
            numberText.setText(newArray[2]);
            salaryText.setText(newArray[3]);
            specialityText.setText(newArray[4]);
            experienceText.setText(newArray[8]);
            vacancyText.setText(newArray[9]);
            cityText.setText(newArray[6]);
            educationText.setText(newArray[7]);
            skillsText.setText(newArray[5]);

        }
        catch (Exception exception){
            exception.getMessage();
        }
    }

    // Метод возврата на предыдущее окно
    public void BackRecruter() throws IOException {
        Stage stageToClose = (Stage) backButton.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recruter-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("recruter-view");
        stage.setScene(scene);
        stage.show();
    }
}
