package ru.job4j.ood.lsp.storeproduct.store;

import ru.job4j.ood.lsp.storeproduct.food.Apple;
import ru.job4j.ood.lsp.storeproduct.food.Food;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/*  Класс ControlQuality во взаимодействии с хранилищами должен обеспечить распределение продуктов
 в зависимости от остатка срока годности:
     3.1. Если срок годности израсходован меньше чем на 25%, продукт должен оказаться в Warehouse;
     3.2. Если срок годности израсходован от 25% до 75%, продукт должен оказаться в Shop;
     3.3. Если срок годности израсходован более, чем на 75%, то продукт должен оказаться в Shop и его цена должна быть снижена на размер скидки в 20 % от первоначальной цены.
     3.4. Если срок годности вышел (израсходован полностью) , продукт должен оказаться в Trash. */
public class ControlQuality {

    AbstractStore shop = new Shop();
    AbstractStore trash = new Trash();
    AbstractStore warehouse = new Warehouse();

    LocalDateTime now = LocalDateTime.now().withNano(0);  /* без милисекунд */

    public void transferByExpiry(Food food) {
        long totalDays = ChronoUnit.DAYS.between(food.createDate, food.expiryDate);
        long expiredDays = ChronoUnit.DAYS.between(food.createDate, now);
        double percentageExpired = ((double) expiredDays / (double) totalDays) * 100;

        /* Определение места размещения продукта в зависимости от процента израсходованного срока годности  */
        if (percentageExpired < 25) {
            warehouse.addFood(food);
            /*       System.out.println("Склад");   */
        } else if (percentageExpired >= 25 && percentageExpired < 75) {
            shop.addFood(food);
            /*        System.out.println("Магазин");   */
        } else if (percentageExpired >= 75 && now.isBefore(food.expiryDate)) {
            shop.addFood(food);
            food.setPrice(food.getPrice() * 0.8); /* Установка цены со скидкой 20%  */
            /*        System.out.println("Магазин");   */
        } else if (now.isAfter(food.expiryDate)) {
            trash.addFood(food);
            /*        System.out.println("Помойка");    */
        }
    }

    public static void main(String[] args) {

        ControlQuality controlQuality = new ControlQuality();
        Food apple = new Apple("greenApple", controlQuality.now.plusDays(10), controlQuality.now.minusDays(3), 100.00, 0);
        Food milk = new Apple("freshMilk", controlQuality.now.plusDays(1), controlQuality.now.minusDays(10), 200.00, 3);
        Food badmilk = new Apple("notFreshMilk", controlQuality.now.minusDays(1), controlQuality.now.minusDays(3), 200.00, 3);
        controlQuality.transferByExpiry(apple);
        controlQuality.transferByExpiry(milk);
        controlQuality.transferByExpiry(badmilk);

        System.out.println("На складе:" + System.lineSeparator() + controlQuality.warehouse.getFoods());
        System.out.println("В магазине:" + System.lineSeparator() + controlQuality.shop.getFoods());
        System.out.println("Помойка:" + System.lineSeparator() + controlQuality.trash.getFoods());
    }
}
