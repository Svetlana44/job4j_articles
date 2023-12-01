package ru.job4j.ood.lsp.storeproduct.store;

import ru.job4j.ood.lsp.storeproduct.food.Food;

import java.util.List;

public class Warehouse extends AbstractStore {

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public List<Food> getFoods() {
        return this.foods;
    }
}
