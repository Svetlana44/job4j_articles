package ru.job4j.ood.lsp.storeproduct.food;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/*  Поля super Food класса будут автоматически доступны в подклассе.  */
public class Apple extends Food {

    public Apple(String name, LocalDateTime expiryDate, LocalDateTime createDate, double price, int discoun) {
        super(name, expiryDate, createDate, price, discoun);
    }
}
