package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    static int errore() {
        System.out.println("Ошибка. Начинай снова.");
        return 0;
    }

    static int comp(int startAt) {
        if (startAt % 3 == 0 && startAt % 5 == 0) {
            System.out.println("FizzBuzz");
        } else if (startAt % 3 == 0) {
            System.out.println("Fizz");
        } else if (startAt % 5 == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(startAt);
        }
        return ++startAt;
    }

    static int myAnsw(int startAt) {
        var io = new Scanner(System.in);
        var answer = io.nextLine();
        if (startAt % 3 == 0 && startAt % 5 == 0) {
            if (!"FizzBuzz".equals(answer)) {
                startAt = errore();
            }
        } else if (startAt % 3 == 0) {
            if (!"Fizz".equals(answer)) {
                startAt = errore();
            }
        } else if (startAt % 5 == 0) {
            if (!"Buzz".equals(answer)) {
                startAt = errore();
            }
        } else {
            if (!String.valueOf(answer).equals(answer)) {
                startAt = errore();
            }
        }
        return ++startAt;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        while (startAt < 100) {
            startAt = comp(startAt);
            startAt = myAnsw(startAt);
        }
    }
}