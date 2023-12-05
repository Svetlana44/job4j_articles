package ru.job4j.lsp.parking.store;

import org.junit.jupiter.api.Test;
import ru.job4j.lsp.parking.model.BigCar;
import ru.job4j.lsp.parking.model.Car;
import ru.job4j.lsp.parking.model.MiniCar;
import ru.job4j.ood.tdd.Cinema3D;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParkingServiceTest extends Cinema3D {
    Car car1 = new MiniCar();
    Car car2 = new MiniCar();
    Car car3 = new BigCar(2);
    Car car4 = new BigCar(3);
    List<Car> cars = List.of(car1, car2, car3, car4);

    @Test
    void checkPlacingWhenFullPlacing() {
        Store parking = new Parking(2, 5);
        ParkingService parkingService = new ParkingService();
        assertThat(parkingService.placing(parking, cars)).isTrue();
    }

    @Test
    void checkPlacingWhenNotFullPlacing() {
        Store parking = new Parking(3, 10);
        ParkingService parkingService = new ParkingService();
        assertThat(parkingService.placing(parking, cars)).isTrue();
    }

    @Test
    void checkPlacingWhenMiniCarOverFullPlacing() {
        Store parking = new Parking(1, 5);
        ParkingService parkingService = new ParkingService();
        assertThat(parkingService.placing(parking, cars)).isFalse();
    }

    @Test
    void checkPlacingWhenBigCarOverFullPlacing() {
        Store parking = new Parking(2, 3);
        ParkingService parkingService = new ParkingService();
        assertThat(parkingService.placing(parking, cars)).isFalse();
    }

    @Test
    void checkPlacingWhenMiniAndBigCarOverFullPlacing() {
        Store parking = new Parking(1, 1);
        ParkingService parkingService = new ParkingService();
        assertThat(parkingService.placing(parking, cars)).isFalse();
    }

    @Test
    void checkPlacingWhenMinusMiniAndMinusBigCarOverFullPlacing() {
        ParkingService parkingService = new ParkingService();

        assertThatThrownBy(() -> new Parking(-1, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Need really size");
        assertThatThrownBy(() -> parkingService.placing(new Parking(-1, -1), cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Need really size");
    }
}