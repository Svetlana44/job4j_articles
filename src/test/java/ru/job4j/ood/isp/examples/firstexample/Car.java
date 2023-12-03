package ru.job4j.ood.isp.examples.firstexample;

public class Car implements Transport {
    @Override
    public void fly() {
    }

    @Override
    public void sweem() {
    }

    @Override
    public void drive() {
        System.out.println("Beee!");
    }
}
