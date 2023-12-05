package ru.job4j.lsp.storeproduct.store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import ru.job4j.ood.lsp.storeproduct.food.Apple;
import ru.job4j.ood.lsp.storeproduct.food.Food;
import ru.job4j.ood.lsp.storeproduct.store.*;
import ru.job4j.ood.tdd.Cinema3D;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest extends Cinema3D {

    LocalDateTime now = LocalDateTime.now().withNano(0);  /* без милисекунд */
    Food apple = new Apple("greenApple", now.plusDays(10), now.minusDays(3), 100.00, 0);
    Food milk = new Apple("freshMilk", now.plusDays(1), now.minusDays(10), 200.00, 3);
    Food badmilk = new Apple("notFreshMilk", now.minusDays(1), now.minusDays(3), 200.00, 3);
    List<Food> foods = List.of(milk, apple, badmilk);

    @Spy
    List<Store> stores = List.of(new Shop(), new Trash(), new Warehouse());


    @Test
    void checkMain() {

        ControlQuality controlQuality = new ControlQuality(stores);
        String expected = "Shop: " + System.lineSeparator()
                + "[Food{name='freshMilk', expiryDate=" + now.plusDays(1) + ", createDate=" + now.minusDays(10) + ", price=160.0, discoun=3}]" + System.lineSeparator()
                + System.lineSeparator()
                + "Trash: " + System.lineSeparator()
                + "[Food{name='notFreshMilk', expiryDate=" + now.minusDays(1) + ", createDate=" + now.minusDays(3) + ", price=200.0, discoun=3}]" + System.lineSeparator()
                + System.lineSeparator()
                + "Warehouse" + System.lineSeparator()
                + "[Food{name='greenApple', expiryDate=" + now.plusDays(10) + ", createDate=" + now.minusDays(3) + ", price=100.0, discoun=0}]" + System.lineSeparator()
                + System.lineSeparator();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        controlQuality.isNotMain(foods, stores);

        Assertions.assertThat(expected).isEqualTo(outputStream.toString());
    }
}
/*
controlQuality.now.plusDays(10), controlQuality.now.minusDays(3), 10
ntrolQuality.now.plusDays(1), controlQuality.now.minusDays(10), 200.
k", controlQuality.now.minusDays(1), controlQuality.now.minusDays(3)

 */