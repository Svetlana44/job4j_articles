package ru.job4j.ood.lsp.storeproduct.store;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import ru.job4j.ood.lsp.storeproduct.food.Apple;
import ru.job4j.ood.lsp.storeproduct.food.Food;
import ru.job4j.ood.tdd.Cinema3D;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest extends Cinema3D {

    @Spy
    ControlQuality controlQuality = new ControlQuality();


    Food apple = new Apple("greenApple", controlQuality.now.plusDays(10), controlQuality.now.minusDays(3), 100.00, 0);
    Food milk = new Apple("freshMilk", controlQuality.now.plusDays(1), controlQuality.now.minusDays(10), 200.00, 3);
    Food badmilk = new Apple("notFreshMilk", controlQuality.now.minusDays(1), controlQuality.now.minusDays(3), 200.00, 3);

    @Test
    void transferByExpiry() {
        controlQuality.transferByExpiry(apple);
        controlQuality.transferByExpiry(milk);
        controlQuality.transferByExpiry(badmilk);
        List<Food> actualShop = controlQuality.shop.foods;
        List<Food> actualTrash = controlQuality.trash.foods;
        List<Food> actualWarehouse = controlQuality.warehouse.foods;
        List<Food> expectedShop = List.of(milk);
        List<Food> expectedTrash = List.of(badmilk);
        List<Food> expectedWarehouse = List.of(apple);
        assertThat(actualShop).isEqualTo(expectedShop);
        assertThat(actualWarehouse).isEqualTo(expectedWarehouse);
        assertThat(actualTrash).isEqualTo(expectedTrash);
    }

    @Test
    void checkMain() {
        String expected = ("На складе:" + System.lineSeparator()
                + "[Food{name='greenApple', expiryDate=" + controlQuality.now.plusDays(10).withNano(0) + ", createDate=" + controlQuality.now.minusDays(3).withNano(0) + ", price=100.0, discoun=0}]" + System.lineSeparator()
                + "В магазине:" + System.lineSeparator()
                + "[Food{name='freshMilk', expiryDate=" + controlQuality.now.plusDays(1).withNano(0) + ", createDate=" + controlQuality.now.minusDays(10).withNano(0) + ", price=160.0, discoun=3}]" + System.lineSeparator()
                + "Помойка:" + System.lineSeparator()
                + "[Food{name='notFreshMilk', expiryDate=" + controlQuality.now.minusDays(1).withNano(0) + ", createDate=" + controlQuality.now.minusDays(3).withNano(0) + ", price=200.0, discoun=3}]" + System.lineSeparator());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ControlQuality.main(null);

        assertThat(expected).isEqualTo(outputStream.toString());
    }
}
/*
controlQuality.now.plusDays(10), controlQuality.now.minusDays(3), 10
ntrolQuality.now.plusDays(1), controlQuality.now.minusDays(10), 200.
k", controlQuality.now.minusDays(1), controlQuality.now.minusDays(3)

 */