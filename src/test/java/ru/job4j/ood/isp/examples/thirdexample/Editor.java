package ru.job4j.ood.isp.examples.thirdexample;

/* Нужно разделение интерфейсов для разных редакторов, текстовых и графических */
public interface Editor {

    void paint();

    void write();

    void writePDF();

}
