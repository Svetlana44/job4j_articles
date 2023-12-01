package ru.job4j.ood.lsp.storeproduct.store;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import ru.job4j.ood.lsp.storeproduct.food.Apple;
import ru.job4j.ood.lsp.storeproduct.food.Food;
import ru.job4j.ood.tdd.Cinema3D;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TrashTest extends Cinema3D {
    @Spy
    AbstractStore trash = new Trash();

    LocalDateTime now = LocalDateTime.now().withNano(0);   /* без милисекунд */
    Food apple = new Apple("greenApple", now.plusDays(10), now.minusDays(3), 30.00, 0);
    Food milk = new Apple("freshMilk", now.plusDays(1), now.minusDays(10), 33.00, 3);

    @Test
    void checkAddFood() {
        trash.addFood(apple);
        trash.addFood(milk);
        List<Food> actual = trash.foods;
        List<Food> expected = List.of(apple, milk);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkGetFoods() {
        List<Food> expected = List.of(apple, milk);
        trash.foods = expected;
        List<Food> actual = trash.getFoods();
        assertThat(actual).isEqualTo(expected);
    }
}