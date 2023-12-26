package ru.job4j.ood.lsp.storeproduct.store;

import ru.job4j.ood.lsp.storeproduct.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    public Warehouse() {
    }

    public Warehouse(List<Food> foods) {
        this.foods = foods;
    }

    List<Food> foods = new ArrayList<>();

    @Override
    public boolean addFood(Food food, double percentageExpired) {
        boolean rsl = false;
        if (percentageExpired < 25) {
            this.foods.add(food);  /*  warehouse  */
            /*       System.out.println("Склад");   */
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getFoods() {
        System.out.println("Warehouse");
        return this.foods;
    }

    @Override
    public void trashFoods() {
        this.foods = new ArrayList<>();
    }
}
