package ru.job4j.ood.isp.menu;

/* Этот класс будет представлять собой консольное приложение, которое позволяет:
Добавить элемент в корень меню;
Добавить элемент к родительскому элементу;
Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
Вывести меню в консоль.   */
public class TodoApp {
    public static void main(String[] args) {
        MenuPrinter simplPrinter = new SimpleMenuPrinter();
        Menu menu = new SimpleMenu();
        ActionDelegate defaultAction = new SomeActionDelegate();
        ActionDelegate onemoreAction = new OneMoreActionDelegate();
        menu.add("parent1", "children1", defaultAction);
        menu.add("parent2", "children2.1", defaultAction);
        menu.add("parent1", "children1", onemoreAction);
        menu.add("parent2", "children2.1", defaultAction);
        menu.add("parent2", "children2.2", defaultAction);
        System.out.println("Вызов действия пункта меню: ");
        menu.select("parent1").get().getActionDelegate().delegate();
        System.out.println("Распечатать меню: ");
        simplPrinter.print(menu);
    }
}
