package ru.job4j.lsp.storeproduct.store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import ru.job4j.ood.lsp.storeproduct.food.Apple;
import ru.job4j.ood.lsp.storeproduct.food.Food;
import ru.job4j.ood.lsp.storeproduct.store.Shop;
import ru.job4j.ood.lsp.storeproduct.store.Store;
import ru.job4j.ood.tdd.Cinema3D;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShopTest extends Cinema3D {

    @Spy
    Store shop = new Shop();

    LocalDateTime now = LocalDateTime.now().withNano(0);  /* без милисекунд */
    Food apple = new Apple("greenApple", now.plusDays(10), now.minusDays(3), 30.00, 0);
    Food milk = new Apple("freshMilk", now.plusDays(1), now.minusDays(10), 33.00, 3);
    Food badmilk = new Apple("notFreshMilk", now.minusDays(1), now.minusDays(3), 200.00, 3);
    List<Food> foods = List.of(apple, milk, badmilk);

    @Test
    void checkAddFood() {

        for (Food food : foods) {
            long totalDays = ChronoUnit.DAYS.between(food.createDate, food.expiryDate);
            long expiredDays = ChronoUnit.DAYS.between(food.createDate, now);
            double percentageExpired = ((double) expiredDays / (double) totalDays) * 100;
            shop.addFood(food, percentageExpired);
        }
        List<Food> actual = shop.getFoods();

        List<Food> expected = List.of(milk);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkGetFoods() {
        List<Food> expected = List.of(apple, milk, badmilk);
        Store shop = new Shop(foods);
        List<Food> actual = shop.getFoods();
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}