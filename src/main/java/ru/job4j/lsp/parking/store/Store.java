package ru.job4j.lsp.parking.store;

import ru.job4j.lsp.parking.model.Car;

import java.util.ArrayList;
import java.util.List;

public abstract class Store {

    public List<Car> cars = new ArrayList<>();

    public abstract void add(Car car);

    public abstract void minus(Car car);

    public abstract int getSizeBigCar();

    public abstract int getsizeMiniCar();
}
