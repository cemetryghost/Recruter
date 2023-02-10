package com.example.kval_ex;

import org.junit.Test;

import static org.junit.Assert.*;

public class TicketTest {

    @Test
    public void getName() {
        Ticket ticket = new Ticket("Марусик Матвей Даниилович", "89875657654", "QA-инженер", "Ожидание");
        assertEquals("Марусик Матвей Даниилович", ticket.getName());
    }

    @Test
    public void getNumber() {
        Ticket ticket = new Ticket("Марусик Матвей Даниилович", "89875657654", "QA-инженер", "Ожидание");
        assertEquals("89875657654", ticket.getNumber());
    }

    @Test
    public void getVacancy() {
        Ticket ticket = new Ticket("Марусик Матвей Даниилович", "89875657654", "QA-инженер", "Ожидание");
        assertEquals("QA-инженер", ticket.getVacancy());
    }

    @Test
    public void getStatus() {
        Ticket ticket = new Ticket("Марусик Матвей Даниилович", "89875657654", "QA-инженер", "Ожидание");
        assertEquals("Ожидание", ticket.getStatus());
    }
}