package ru.job4j.ood.lsp.firstexample;

public class Prog {
    public void act(int ficha) {
        if (ficha < 0) {
            throw new IllegalArgumentException("Need fichas");
        }
    }
}