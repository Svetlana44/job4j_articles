package ru.job4j.ood.isp.menu;

public class OneMoreActionDelegate implements ActionDelegate {
    @Override
    public void delegate() {
        System.out.println("And one more action.");
    }

    @Override
    public String toString() {
        return "OneMoreActionDelegate{ещё один экшен}";
    }
}
