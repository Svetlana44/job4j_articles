package ru.job4j.ood.tdd;

import java.util.Objects;

public class AccountCinema implements Account {
    int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountCinema that = (AccountCinema) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}