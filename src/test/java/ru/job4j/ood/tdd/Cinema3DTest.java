package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.powermock.api.mockito.PowerMockito.spy;
/* import static org.assertj.core.api.Assertions.*;  */

@Disabled /* ("Тесты отключены. Удалить аннотацию после реализации всех методов.")*/
class Cinema3DTest extends Cinema3D {

    @Spy
    Account accountSpy = spy(new AccountCinema());
    Cinema cinemaSpy = spy(new Cinema3D());
    Session sessionSpy = spy(new Session3D());
    Ticket ticketSpy = spy(new Ticket3D(accountSpy, 1, 1, Calendar.getInstance()));
    List<Session> sessionSpyList = spy(new ArrayList<>());

    /* testFind() */
    @Test
    void testFindSession() {
        sessionSpyList.add(sessionSpy);
        List<Session> expected = sessionSpyList;
        List<Session> actual = cinemaSpy.findSession(s -> s.equals(sessionSpy));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testFindAccount() {
        cinemaSpy.findAccount(acc -> acc.equals(accountSpy));
    }

    @Test
    void testFindTicket() {
        cinemaSpy.findTicket(ticket -> ticket.equals(ticketSpy));
    }

    @Test
    void testBuyTicket() {
        Ticket ticket = cinemaSpy.buy(accountSpy, 1, 1, Calendar.getInstance());
        assertThat(ticket).isEqualTo(ticketSpy);
    }

    /*  Нельзя использовать spy() для создания объекта типа List,
     так как spy() используется для создания шпиона (spy) существующего объекта.  */
    @Test
    void testAdd() {
        List<Session> sessionsActual = new ArrayList<>();
        List<Session> sessionsExpected = new ArrayList<>();
        Cinema3D cinema = new Cinema3D();
        cinema.setSessions(sessionsActual);
        sessionsExpected.add(sessionSpy);
        cinema.add(sessionSpy);
        assertThat(sessionsExpected).isEqualTo(sessionsActual);
    }

    @Test
    public void whenBuyThenGetTicket() {
  /*      Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();   */
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinemaSpy.buy(accountSpy, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D(accountSpy, 1, 1, date));
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.findSession(ses -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }
}