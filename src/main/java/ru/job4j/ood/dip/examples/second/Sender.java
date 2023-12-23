package ru.job4j.ood.dip.examples.second;

/*
Тут нужен общий интерфейс для типов рассылки Crm
*/

public class Sender {
    void send(){
        Sms sms = new Sms();
        Email email = new Email();
    }
}
