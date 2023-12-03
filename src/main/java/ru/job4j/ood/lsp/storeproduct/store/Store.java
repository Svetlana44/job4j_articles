package ru.job4j.ood.lsp.storeproduct.store;

import ru.job4j.ood.lsp.storeproduct.food.Food;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public interface Store {

    boolean addFood(Food food, double percentageExpired);

    List<Food> getFoods();
}
