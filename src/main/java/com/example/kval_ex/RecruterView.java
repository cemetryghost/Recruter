package com.example.kval_ex;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

// Класс рекрутера

public class RecruterView implements Initializable {

    public ObservableList<String> status = UserApplicationView.pathToList("files\\status.txt");
    @FXML private ComboBox<String> statusList;
    @FXML private Button applicationButton, vacancyButton, changeStatusButton, deleteApplicationButton, exitButton, viewingApplicationButton;
    @FXML private TableColumn<Ticket, String> col_name, col_number, col_status, col_vacancy;
    @FXML private TableView<Ticket> applicationTable;
    ObservableList<Ticket> list = FXCollections.observableArrayList();

    public static int iindex;

    // Переход на окно с заявками
    @FXML
    void ApplicationButton() throws IOException {
        Stage stageToClose = (Stage) applicationButton.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("recruter-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("recruter-view");
        stage.setScene(scene);
        stage.show();
    }

    // Переход на окно с вакансиями

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

    // Выход из программы
    @FXML
    void ExitButton(){
        System.exit(1);
    }

    // Метод для изменения статуса заявки

    public void ChangeStatusButton() throws Exception{
        String text = "";
        String line;

        // Читаем пока не конец строки из файла выбранного элемента

        BufferedReader bufferedReader = new BufferedReader(new FileReader("files\\application.txt"));
        if(getSelected() != -1){
            while((line = bufferedReader.readLine()) != null){
                text += line + "\r\n";
            }

            // Массив для изменения статуса заявки

            String[] array = text.split("\r\n");
            String change = array[getSelected()];
            String[] newArray = change.split(":");
            change = change.replace(newArray[10], statusList.getSelectionModel().getSelectedItem());

            text = text.replace(array[getSelected()], change);

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("files\\application.txt"));
            bufferedWriter.write(text);
            bufferedWriter.flush();
            Update();

            JOptionPane.showMessageDialog(null, "Статус заявки успешно изменен!");
        }
        else{
            JOptionPane.showMessageDialog(null, "Заявка не выбрана!");
        }
    }

    // Метод для удаления заявки

    public void DeleteApplicationButton() {
        String text = "";
        String line;
        if(getSelected() != -1){
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader("files\\application.txt"))){
                while((line = bufferedReader.readLine()) != null){
                    text += line + "\r\n";
                }

                String[] array = text.split("\r\n");
                array[getSelected()] = "delete";

                text = "";
                for(String a : array){
                    if(!a.contains("delete")){
                        text += a + "\r\n";
                    }
                }

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("files\\application.txt"));
                bufferedWriter.write(text);
                bufferedWriter.flush();
                Update();

                JOptionPane.showMessageDialog(null, "Заявка успешно удалена!");
            }
            catch (Exception exception){
                exception.getMessage();
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Заявка не выбрана!");
        }
    }

    // Метод для отображения полной заявки пользователя

    public void ViewingApplicationButton() throws Exception{
        if(getSelected() != -1){
            Stage stageToClose = (Stage) applicationButton.getScene().getWindow();
            stageToClose.close();

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("full-application.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("full-application");
            stage.setScene(scene);
            stage.show();
        }
        else {
            JOptionPane.showMessageDialog(null,"Заявка не выбрана!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Update();
    }

    // Метод проверки выбора записи в таблице для дальнейших действий

    public int getSelected(){
        int index = -1;
        iindex = index;
        try{
            index = applicationTable.getSelectionModel().getSelectedIndex();
            if(index < -1){
                return -1;
            }
            else{
                iindex = index;
                return index;
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return index;
    }

    // Метод для обновления данных в таблице

    public void Update(){
        applicationTable.getItems().clear();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("files\\application.txt"))){
            String line;
            statusList.getItems().clear();
            statusList.getItems().addAll(status);

            while ((line = bufferedReader.readLine()) != null){
                String[] text = line.split(":");
                list.add(new Ticket(text[0], text[2], text[9], text[10]));
            }
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_number.setCellValueFactory(new PropertyValueFactory<>("number"));
            col_vacancy.setCellValueFactory(new PropertyValueFactory<>("vacancy"));
            col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

            applicationTable.setItems(list);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}


