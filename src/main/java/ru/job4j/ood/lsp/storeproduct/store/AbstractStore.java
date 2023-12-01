package ru.job4j.ood.lsp.storeproduct.store;

import ru.job4j.ood.lsp.storeproduct.food.Food;

import java.util.ArrayList;
import java.util.List;

/* реализация общей логики хранилищ  */
public abstract class AbstractStore {

    List<Food> foods = new ArrayList<>();

    public abstract void addFood(Food food);

    public abstract List<Food> getFoods();
}
