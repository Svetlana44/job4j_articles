package ru.job4j.ood.lsp.storeproduct.store;

import ru.job4j.ood.lsp.storeproduct.food.Food;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore {

    public Trash() {
    }

    public Trash(List<Food> foods) {
        this.foods = foods;
    }

    List<Food> foods = new ArrayList<>();

    @Override
    public boolean addFood(Food food, double percentageExpired) {
        boolean rsl = false;
        if (LocalDateTime.now().isAfter(food.expiryDate)) {
            this.foods.add(food);    /*  trash   */
            /*        System.out.println("Помойка");    */
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getFoods() {
        System.out.println("Trash: ");
        return this.foods;
    }
}