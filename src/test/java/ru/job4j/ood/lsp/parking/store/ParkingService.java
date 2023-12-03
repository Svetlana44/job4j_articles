package ru.job4j.ood.lsp.parking.store;

import ru.job4j.ood.lsp.parking.model.BigCar;
import ru.job4j.ood.lsp.parking.model.Car;
import ru.job4j.ood.lsp.parking.model.MiniCar;

import java.util.List;

public class ParkingService {

    public static void main(String[] args) {
        Car car1 = new MiniCar();
        Car car2 = new MiniCar();
        Car car3 = new BigCar(2);
        Car car4 = new BigCar(3);
        List<Car> cars = List.of(car1, car2, car3, car4);


    }
}
