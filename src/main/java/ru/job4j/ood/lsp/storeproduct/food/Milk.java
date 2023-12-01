package ru.job4j.ood.lsp.storeproduct.food;

import java.time.LocalDateTime;

/*  Поля super Food класса будут автоматически доступны в подклассе.  */
public class Milk extends Food {
    public Milk(String name, LocalDateTime expiryDate, LocalDateTime createDate, double price, int discoun) {
        super(name, expiryDate, createDate, price, discoun);
    }
}
