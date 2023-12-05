package ru.job4j.lsp.parking.store;

import ru.job4j.lsp.parking.model.Car;

public class Parking extends Store {
    protected int sizeMiniCar;
    protected int sizeBigCar;

    public Parking(int sizeMiniCar, int sizeBigCar) {

        if (sizeMiniCar < 0 || sizeBigCar < 0) {
            throw new IllegalArgumentException("Need really size");
        }

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

    @Override
    public int getSizeBigCar() {
        return sizeBigCar;
    }

    @Override
    public int getsizeMiniCar() {
        return sizeMiniCar;
    }
}
