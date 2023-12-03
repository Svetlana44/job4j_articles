package ru.job4j.ood.lsp.storeproduct.store;

import ru.job4j.ood.lsp.storeproduct.food.Food;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {
    public Shop() {
    }

    public Shop(List<Food> foods) {
        this.foods = foods;
    }

    List<Food> foods = new ArrayList<>();

    @Override
    public boolean addFood(Food food, double percentageExpired) {
        boolean rsl = false;
        if (percentageExpired >= 25 && percentageExpired < 75) {
            this.foods.add(food);                    /*  shop   */
            /*        System.out.println("Магазин");   */
            rsl = true;
        } else if (percentageExpired >= 75 && LocalDateTime.now().isBefore(food.expiryDate)) {
            this.foods.add(food);                    /*  shop   */
            food.setPrice(food.getPrice() * 0.8); /* Установка цены со скидкой 20%  */
            /*        System.out.println("Магазин");   */
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getFoods() {
        System.out.println("Shop: ");
        return this.foods;
    }
}
