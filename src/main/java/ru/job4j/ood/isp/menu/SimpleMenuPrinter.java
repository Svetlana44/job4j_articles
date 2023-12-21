package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class SimpleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        SimpleMenu simpleMenu = (SimpleMenu) menu;
        Iterator<Menu.MenuItemInfo> menuIterator = simpleMenu.iterator();
        while (menuIterator.hasNext()) {
            Menu.MenuItemInfo menuPr = menuIterator.next();
            String str = menuPr.getNumber();
            String spaces = "";
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '.') {
                    spaces += " ";
                }
            }
            System.out.println(spaces + menuPr);
        }
    }
}
