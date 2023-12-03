package ru.job4j.ood.isp.examples.secondexample;

public class PaperBox implements Store {
    boolean cool = true;

    @Override
    public void freeze() {
        System.out.println("Nothing");
    }

    @Override
    public void care() {
        cool = true;
    }
}
