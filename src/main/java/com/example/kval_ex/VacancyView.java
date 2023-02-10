package com.example.kval_ex;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

// Класс для обработки вакансий

public class VacancyView implements Initializable{

    @FXML private TableView<Vacancy> vacancyTable;

    @FXML private TableColumn<Vacancy, String> col_AllVacancy;

    @FXML private Button addVacancyButton, changeVacancyButton, deleteVacancyButton, backApplicationButton, vacancyButton;

    @FXML private TextField vacancyField;
    ObservableList<Vacancy> list = FXCollections.observableArrayList();

    // Метод для добавления вакансий

    @FXML
    void AddVacancyButton() {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("files\\vacancy.txt", true))){
            String text = "";
            String line;
            BufferedReader bufferedReader = new BufferedReader(new FileReader("files\\vacancy.txt"));

            while ((line = bufferedReader.readLine()) != null){
                text += line;
            }

            if(vacancyField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Введите новую вакансию в текстовое поле!");
            }
            else if(!vacancyField.getText().isEmpty() && !text.contains(vacancyField.getText())){
                bufferedWriter.write(vacancyField.getText() + "\r\n");
                bufferedWriter.flush();
                Update();

                JOptionPane.showMessageDialog(null,"Вакансия успешно добавлена!");
            }
            else{
                JOptionPane.showMessageDialog(null,"Данная вакансия уже существует!");
            }
        }
        catch (Exception e){

        }
    }

    // Метод для изменения вакансий

    @FXML
    void ChangeVacancyButton() {
        if(!vacancyField.getText().isEmpty() && vacancyTable.getSelectionModel().getSelectedItem() != null){
            String temp = vacancyField.getText();
            int choose = vacancyTable.getSelectionModel().getSelectedIndex();
            int number = 0;
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader("files\\vacancy.txt"))){
                String text = "";
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    if(number == choose){
                        text += temp + "\r\n";
                        number++;
                    }
                    else{
                        text += line + "\r\n";
                        number++;
                    }
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("files\\vacancy.txt"));
                bufferedWriter.write(text);
                bufferedWriter.flush();
                bufferedWriter.close();
                Update();

                JOptionPane.showMessageDialog(null,"Вакансия успешно изменина!");
            }
            catch (Exception e){
                e.getMessage();
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"Выберите заявку, которую хотите изменить!");
        }
    }

    // Метод для удаления вакансий

    @FXML
    void DeleteVacancyButton() throws Exception{
        if(vacancyTable.getSelectionModel().getSelectedItem() != null){
            int choose = vacancyTable.getSelectionModel().getSelectedIndex();
            int number = 0;
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader("files\\vacancy.txt"))){
                String text = "";
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    if(number != choose){
                        text += line + "\r\n";
                        number++;
                    }
                    else{
                        number++;
                    }
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("files\\vacancy.txt"));
                bufferedWriter.write(text);
                bufferedWriter.flush();
                bufferedWriter.close();
                Update();

                JOptionPane.showMessageDialog(null,"Вакансия успешно удалена!");
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"Выберите заявку, которую хотите удалить!");
        }
    }

    @FXML
    void BackApplicationButton() throws IOException {
        Stage stageToClose = (Stage) backApplicationButton.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recruter-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("recruter-view");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void VacancyButton() throws IOException {
        Stage stageToClose = (Stage) vacancyButton.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vacancy-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("vacancy-view");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ExitButtonVacancy() {
        System.exit(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Update();
    }

    public void getSelected() {
        vacancyField.setText(vacancyTable.getSelectionModel().getSelectedItem().getVacancy());
    }

    // Метод для обновления таблицы с вакансиями

    public void Update(){
        vacancyTable.getItems().clear();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("files\\vacancy.txt"))){
            String line;

            while ((line = bufferedReader.readLine()) != null){
                list.add(new Vacancy(line + "\r\n"));
            }
            col_AllVacancy.setCellValueFactory(new PropertyValueFactory<>("vacancy"));
            vacancyTable.setItems(list);
        }
        catch (Exception exception){
            exception.getMessage();
        }
    }
}

