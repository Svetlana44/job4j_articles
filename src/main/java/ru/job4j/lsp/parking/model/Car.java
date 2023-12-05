package ru.job4j.lsp.parking.model;

public abstract class Car {
    protected int size;

    public Car(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Need really size/");
        }
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
