package ru.job4j.ood.dip.examples.first;
/*
Здесь Apple зависит от конкретного Knife
нужно сделать Knife, как реализацию интерфейса Stuff и сервисный класс, в котором будет резаться яблоко
* */

public class Apple {
    void cutApple() {
        Knife knife = new Knife();
        knife.cut(this);
    }
}
