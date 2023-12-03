package ru.job4j.ood.isp.examples.secondexample;

public class Holodilnik implements Store {
    int tempecha = 0;
    boolean cool = true;

    @Override
    public void freeze() {
        tempecha = -18;
    }

    @Override
    public void care() {
        cool = true;
    }
}
