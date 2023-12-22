package ru.job4j.ood.isp.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class SimpleMenuPrinterTest {

    MenuPrinter simplPrinter = new SimpleMenuPrinter();
    Menu menu = new SimpleMenu();
    @Spy
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    @Spy
    PrintStream printStream = new PrintStream(out);

    @Test
    void print() {
        MenuPrinter simplPrinter = new SimpleMenuPrinter();
        Menu menu = new SimpleMenu();
        ActionDelegate defaultAction = new SomeActionDelegate();
        ActionDelegate onemoreAction = new OneMoreActionDelegate();
        menu.add("parent1", "children1", defaultAction);
        menu.add("parent2", "children2.1", defaultAction);
        menu.add("parent1", "children1", onemoreAction);
        menu.add("parent2", "children2.1", defaultAction);
        menu.add("parent2", "children2.2", defaultAction);
        menu.add("parent2", "children2.3", defaultAction);

        System.setOut(printStream);
        String expected = " 1.'parent1'" + System.lineSeparator()
                + "  1.1.'children1'" + System.lineSeparator()
                + " 2.'parent2'" + System.lineSeparator()
                + "  2.1.'children2.1'" + System.lineSeparator()
                + "  2.2.'children2.2'" + System.lineSeparator()
                + "  2.3.'children2.3'" + System.lineSeparator();
        simplPrinter.print(menu);
        String actual = out.toString();
        Assertions.assertThat(actual).isEqualTo(expected);
    }
    /*
Это позволит обнулить содержимое `ByteArrayOutputStream` и заменить текущий поток вывода на новый `PrintStream`.
    outputStream.reset();
System.setOut(printStream);
    */
}