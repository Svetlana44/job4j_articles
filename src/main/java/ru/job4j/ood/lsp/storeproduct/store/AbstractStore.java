package ru.job4j.ood.lsp.storeproduct.store;

import ru.job4j.ood.lsp.storeproduct.food.Food;

import java.util.List;

/* реализация общей логики хранилищ  */
public abstract class AbstractStore implements Store {

    /*    public abstract void addFood(Food food);  */

    /*    public abstract List<Food> getFoods();  */
    public void transferByExpiry(Food food, List<Store> stores) {
        /* Определение места размещения продукта в зависимости от процента израсходованного срока годности  */
    }
}
