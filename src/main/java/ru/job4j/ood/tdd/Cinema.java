package ru.job4j.ood.tdd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Cinema {

    List<Session> findSession(Predicate<Session> filter);

    Account findAccount(Predicate<Session> filter);

    Account findTicket(Predicate<Session> filter);

    Ticket buy(Account account, int row, int column, Calendar date);

    void add(Session session);
}