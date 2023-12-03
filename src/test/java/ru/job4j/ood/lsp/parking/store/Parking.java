package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.Car;

public class Parking extends Store {
    int sizeMiniCar;
    int sizeBigCar;

    public Parking(int sizeMiniCar, int sizeBigCar) {
        this.sizeMiniCar = sizeMiniCar;
        this.sizeBigCar = sizeBigCar;
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
