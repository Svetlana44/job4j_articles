package ru.job4j.ood.lsp.thirdexample;

public class Count {
    int num;

    public void check(int i) {
        if (i < 100) {
            num = i;
        }
    }

    public void setNum(int i) {
        if (i < 100) {
            this.num = i;
        }
    }
}
