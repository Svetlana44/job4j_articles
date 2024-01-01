package ru.job4j.concurrent;

/*
создание переменной another идет в нити main,
 но вызов выражения описанного в конструкторе идет (вывод на консоль) уже в нити с именем Thread-0.
  Метод Thread#start() указывает виртуальной машине,
   что операторы описанные в конструкторе нужно запустить в отдельной нити.
 Если убрать этот оператор, то вывода имени второй нити не будет */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        System.out.println(Thread.currentThread().getName());
    }
}