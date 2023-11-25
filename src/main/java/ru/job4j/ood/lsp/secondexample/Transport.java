package ru.job4j.ood.lsp.secondexample;

public class Transport {
    public void move(int fuel) {
        if (fuel <= 0) {
            throw new IllegalArgumentException("Need fuel for moving.");
        }
    }
}
