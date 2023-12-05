package ru.job4j.lsp.parking.store;

import ru.job4j.lsp.parking.model.Car;

import java.util.List;

public class ParkingService {
    /*  Car car1 = new MiniCar();
        Car car2 = new MiniCar();
        Car car3 = new BigCar(2);
        Car car4 = new BigCar(3);
        List<Car> cars = List.of(car1, car2, car3, car4);
*/

    public boolean placing(Store parking, List<Car> cars) {

        int count = parking.getsizeMiniCar() + parking.getSizeBigCar();
        for (Car car : cars) {
            if (count >= car.getSize()) {
                count -= car.getSize();
            } else {
                return false;
            }
        }
        return true;
    }

/*    public static void main(String[] args) {
        Parking parking = new Parking(-1, -1);
    }*/
}
