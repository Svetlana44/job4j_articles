package ru.job4j.ood.lsp.storeproduct.store;

import ru.job4j.ood.lsp.storeproduct.food.Food;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/*  Класс ControlQuality во взаимодействии с хранилищами должен обеспечить распределение продуктов
 в зависимости от остатка срока годности:
     3.1. Если срок годности израсходован меньше чем на 25%, продукт должен оказаться в Warehouse;
     3.2. Если срок годности израсходован от 25% до 75%, продукт должен оказаться в Shop;
     3.3. Если срок годности израсходован более, чем на 75%, то продукт должен оказаться в Shop и его цена должна быть снижена на размер скидки в 20 % от первоначальной цены.
     3.4. Если срок годности вышел (израсходован полностью) , продукт должен оказаться в Trash. */
public class ControlQuality {
    public List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void isNotMain(List<Food> foods, List<Store> stores) {

     /*   List<Store> stores = List.of(new Shop(), new Trash(), new Warehouse());

        ControlQuality controlQuality = new ControlQuality(stores);  */

        LocalDateTime now = LocalDateTime.now().withNano(0);  /* без милисекунд */

   /*     Food apple = new Apple("greenApple", now.plusDays(10), now.minusDays(3), 100.00, 0);
        Food milk = new Apple("freshMilk", now.plusDays(1), now.minusDays(10), 200.00, 3);
        Food badmilk = new Apple("notFreshMilk", now.minusDays(1), now.minusDays(3), 200.00, 3);

        List<Food> foods = List.of(apple, milk, badmilk);  */
        for (Food food : foods) {
            long totalDays = ChronoUnit.DAYS.between(food.createDate, food.expiryDate);
            long expiredDays = ChronoUnit.DAYS.between(food.createDate, now);
            double percentageExpired = ((double) expiredDays / (double) totalDays) * 100;

            for (Store store : stores) {
                if (store.addFood(food, percentageExpired)) {
                    break;
                }
            }
        }

        for (Store store : stores) {
            System.out.println(store.getFoods() + System.lineSeparator());
        }
    }

    /* Динамическое перераспределение
    извлекать все продукты и перераспределять их заново*/
    public void resort(ControlQuality controlQuality) {
        List<Store> stores = controlQuality.stores;
        List<Food> foods = new ArrayList<>();
        for (Store store : stores) {
            for (Food food : store.getFoods()) {
                foods.add(food);
            }
            store.trashFoods();
        }
        isNotMain(foods, stores);
    }

 /*   public static void main(String[] args) {

        List<Store> stores = List.of(new Shop(), new Trash(), new Warehouse());

        ControlQuality controlQuality = new ControlQuality(stores);

        LocalDateTime now = LocalDateTime.now().withNano(0);  /* без милисекунд */

 /*       Food apple = new Apple("greenApple", now.plusDays(10), now.minusDays(3), 100.00, 0);
        Food milk = new Apple("freshMilk", now.plusDays(1), now.minusDays(10), 200.00, 3);
        Food badmilk = new Apple("notFreshMilk", now.minusDays(1), now.minusDays(3), 200.00, 3);

        List<Food> foods = List.of(apple, milk, badmilk);
        for (Food food : foods) {
            long totalDays = ChronoUnit.DAYS.between(food.createDate, food.expiryDate);
            long expiredDays = ChronoUnit.DAYS.between(food.createDate, now);
            double percentageExpired = ((double) expiredDays / (double) totalDays) * 100;

            for (Store store : stores) {
                if (store.addFood(food, percentageExpired)) {
                    break;
                }
            }
        }

        for (Store store : stores) {
            System.out.println(store.getFoods() + System.lineSeparator());
        }
    }   */
}
/*
        Store shop = new Shop();
        Store trash = new Trash();
        Store warehouse = new Warehouse();

    public void transferByExpiry(Food food) {
        LocalDateTime now = LocalDateTime.now().withNano(0);  /* без милисекунд */  /*
long totalDays = ChronoUnit.DAYS.between(food.createDate, food.expiryDate);
    long expiredDays = ChronoUnit.DAYS.between(food.createDate, now);
    double percentageExpired = ((double) expiredDays / (double) totalDays) * 100;

/* Определение места размещения продукта в зависимости от процента израсходованного срока годности  */  /*
        if (percentageExpired < 25) {
        stores.get(2).addFood(food);  /*  warehouse  */
/*       System.out.println("Склад");   */  /*
        System.out.println("Склад");
        } else if (percentageExpired >= 25 && percentageExpired < 75) {
        stores.get(0).addFood(food);                     /*  shop   */
/*        System.out.println("Магазин");   */  /*
        System.out.println("Магазин");
        } else if (percentageExpired >= 75 && now.isBefore(food.expiryDate)) {
        stores.get(0).addFood(food);                     /*  shop   */  /*
        food.setPrice(food.getPrice() * 0.8); /* Установка цены со скидкой 20%  */
/*        System.out.println("Магазин");   */ /*
        System.out.println("Магазин");
        } else if (now.isAfter(food.expiryDate)) {
        stores.get(1).addFood(food);    /*  trash   */
/*        System.out.println("Помойка");    */ /*
        System.out.println("Помойка");
        }
        }


*/