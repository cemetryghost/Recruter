package com.example.kval_ex;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;

// Класс пользователя

public class UserApplicationView{

    public ObservableList<String> city = pathToList("Recruter-master\\files\\city.txt");
    public ObservableList<String> education = pathToList("Recruter-master\\files\\education.txt");
    public ObservableList<String> experience = pathToList("Recruter-master\\files\\experience.txt");
    public ObservableList<String> vacancy = pathToList("Recruter-master\\files\\vacancy.txt");

    @FXML
    void initialize() {
        try {
            cityList.getItems().addAll(city);
            educationList.getItems().addAll(education);
            experienceList.getItems().addAll(experience);
            vacancyList.getItems().addAll(vacancy);

        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    // Заполение списка элементами из файла

    public static ObservableList<String> pathToList(String path){
        ObservableList<String> list = FXCollections.observableArrayList(); // Создаётся новый лист
        String line; // Создаётся строка для будущей записи

        // Чтение файла и запись его в лист
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))){
            while ((line = br.readLine()) != null) { // Читаем пока не конец файла
                list.add(line); // Записываем
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @FXML private TextField FIOfield, dateField, numberField, salaryField, specialityField;

    @FXML private TextArea skillsArea;

    @FXML private ComboBox<String> cityList, educationList, experienceList, vacancyList;

    @FXML private Button sendButton;

    // Метод с проверкой на отсутсвтие пустых поле и записью с определенным форматом

    @FXML
    void SendButton() throws IOException {
        if (!FIOfield.getText().isEmpty() &&
                !dateField.getText().isEmpty() &&
                !numberField.getText().isEmpty() &&
                !salaryField.getText().isEmpty() &&
                !specialityField.getText().isEmpty() &&
                !specialityField.getText().isEmpty() &&
                !skillsArea.getText().isEmpty() &&
                !skillsArea.getText().isEmpty() &&
                !cityList.getSelectionModel().isEmpty() &&
                !educationList.getSelectionModel().isEmpty() &&
                !experienceList.getSelectionModel().isEmpty() &&
                !vacancyList.getSelectionModel().isEmpty()){

            String text = String.format("%s:%s:%s:%s:%s:%s:%s:%s:%s:%s:Ожидание",
                    FIOfield.getText(),
                    dateField.getText(),
                    numberField.getText(),
                    salaryField.getText(),
                    specialityField.getText(),
                    skillsArea.getText(),
                    cityList.getSelectionModel().getSelectedItem(),
                    educationList.getSelectionModel().getSelectedItem(),
                    experienceList.getSelectionModel().getSelectedItem(),
                    vacancyList.getSelectionModel().getSelectedItem()
                    );
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Recruter-master\\files\\application.txt", true))){
                bufferedWriter.write(text + "\r\n");
                bufferedWriter.flush();
            }

            Stage stageToClose = (Stage) sendButton.getScene().getWindow();
            stageToClose.close();

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("application-sent.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("application-sent");
            stage.setScene(scene);
            stage.show();
        }
        else {
            JOptionPane.showMessageDialog(null, "Убедитесь в отсутствии пустых полей!");
        }

    }

}
