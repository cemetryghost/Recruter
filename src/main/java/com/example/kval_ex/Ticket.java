package com.example.kval_ex;

// Класс инициализации объектов для таблицы

public class Ticket {
    public String name, number, vacancy, status, newStatus = "";

    public Ticket(String name, String number, String vacancy, String status){
        this.name = name;
        this.number = number;
        this.vacancy = vacancy;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public String getVacancy() {
        return vacancy;
    }

    public String getStatus() {
        return status;
    }
}
