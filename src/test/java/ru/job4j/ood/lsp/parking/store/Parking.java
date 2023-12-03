package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;

public class Parking extends Store {
    int size;

    public Parking(int size) {
        this.size = size;
    }

    @Override
    public void add(Car car) {
        this.cars.add(car);
    }

    @Override
    public void minus(Car car) {
        this.cars.remove(car);
    }
}
