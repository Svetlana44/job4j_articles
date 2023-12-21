package ru.job4j.ood.isp.menu;

public class SomeActionDelegate implements ActionDelegate {
    @Override
    public void delegate() {
        System.out.println("First some action");
    }

    @Override
    public String toString() {
        return "SomeActionDelegate{тут могла быть Ваша реклама...}";
    }
}
