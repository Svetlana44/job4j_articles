package ru.job4j.ood.isp.examples.secondexample;

/* Нужно разделение интерфейсов холодильного хранилища и просто коробки без охлаждения */
public interface Store {
    void freeze();

    void care();
}
