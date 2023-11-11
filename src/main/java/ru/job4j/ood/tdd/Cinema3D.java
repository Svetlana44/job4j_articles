package ru.job4j.ood.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {

    List<Session> sessions = new ArrayList<>();

    List<Ticket> tickets = new ArrayList<>();

    @Override
    public List<Session> findSession(Predicate<Session> filter) {
        Session session = new Session3D();
        List<Session> sessionList = new ArrayList<>();
        if (filter.test(session)) {
            sessionList.add(session);
        }
        return sessionList;
    }

    @Override
    public Account findAccount(Predicate<Session> filter) {
        return null;
    }

    @Override
    public Account findTicket(Predicate<Session> filter) {
        return null;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        Ticket ticket = new Ticket3D(account, row, column, date);
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public void add(Session session) {

    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
