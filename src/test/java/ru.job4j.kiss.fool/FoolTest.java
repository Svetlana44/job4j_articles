package ru.job4j.kiss.fool;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;


class FoolTest extends Fool {

    @Test
    void testErrore() {
        /*   Создаем объект ByteArrayOutputStream для перехвата вывода на консоль  */
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();   /* поток байт */
             PrintStream printStream = new PrintStream(outputStream)   /*  перевод байт в символы */
        ) {
            System.setOut(printStream);   /* переключение вывода в поток */
            /*  Вызываем метод, который должен вывести "Ошибка. Начинай снова."   */
            errore();

            /*  Проверяем, что вывод на консоль соответствует ожидаемому   */
            assertEquals("Ошибка. Начинай снова.", outputStream.toString().trim());

            /*  Восстанавливаем стандартный вывод на консоль  */
            System.setOut(System.out);
            /*  проверить, что метод при ошибке возвращает 0  */
            Assertions.assertThat(errore()).isEqualTo(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*       if (startAt % 3 == 0 && startAt % 5 == 0) {
            System.out.println("FizzBuzz");
        } else if (startAt % 3 == 0) {
            System.out.println("Fizz");
        } else if (startAt % 5 == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(startAt);
        }
        return ++startAt;  */

    @Test
    void testComp3And5() {
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(outStream)) {
            System.setOut(printStream);

            assertEquals(comp(15), 16);
            assertEquals("FizzBuzz", outStream.toString().trim());

            System.setOut(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testComp3() {
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(outStream)) {
            System.setOut(printStream);

            assertEquals(comp(3), 4);
            assertEquals("Fizz", outStream.toString().trim());

            System.setOut(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testComp5() {
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(outStream)) {
            System.setOut(printStream);

            assertEquals(comp(5), 6);
            assertEquals("Buzz", outStream.toString().trim());

            System.setOut(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCompNot3AndNot5() {
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(outStream)) {
            System.setOut(printStream);

            assertEquals(comp(4), 5);
            assertEquals("4", outStream.toString().trim());

            System.setOut(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* if (startAt % 3 == 0 && startAt % 5 == 0) {
            if (!"FizzBuzz".equals(answer)) {
                startAt = errore();
            }
        } else if (startAt % 3 == 0) {
            if (!"Fizz".equals(answer)) {
                startAt = errore();
            }
        } else if (startAt % 5 == 0) {
            if (!"Buzz".equals(answer)) {
                startAt = errore();
            }
        } else {
            if (!String.valueOf(answer).equals(answer)) {
                startAt = errore();
            }
        }
        return ++startAt;
*/
    @Spy
    /* Создание спайа(spy-следит, что вызван метод с опр. аргументами) для System.in */
            ByteArrayInputStream input = new ByteArrayInputStream("FizzBuzz".getBytes());
    InputStream spyInputStream = spy(input);

    @Test
    void testMyAnsw3And5() {
        System.setIn(spyInputStream);
        assertEquals(myAnsw(45), 46);
    }

    @Spy
    /* Создание спайа(spy-следит, что вызван метод с опр. аргументами) для System.in */
            ByteArrayInputStream inputFizz = new ByteArrayInputStream("Fizz".getBytes());
    InputStream spyInputStreamFizz = spy(inputFizz);

    @Test
    void testMyAnsw3() {
        System.setIn(spyInputStreamFizz);

        /* Выполнение вашего тестового кода, который будет использовать System.in   */
        assertEquals(myAnsw(3), 4);

        /* Восстановление оригинального System.in  */
        System.setIn(System.in);
    }

    @Spy
    /* Создание спайа(spy-следит, что вызван метод с опр. аргументами) для System.in */
            ByteArrayInputStream inputBuzz = new ByteArrayInputStream("Buzz".getBytes());
    InputStream spyInputStreamBuzz = spy(inputBuzz);

    @Test
    void testMyAnsw5() {
        System.setIn(spyInputStreamBuzz);

        /* Выполнение вашего тестового кода, который будет использовать System.in   */
        assertEquals(myAnsw(5), 6);
    }

    @Spy
    /* Создание спайа(spy-следит, что вызван метод с опр. аргументами) для System.in */
            ByteArrayInputStream inputNot3Not5 = new ByteArrayInputStream("Any NOT FizzBuzz".getBytes());
    InputStream spyInputStreamNot3Not5 = spy(inputNot3Not5);

    @Test
    void testMyAnsw3And5Err() {
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(outStream)) {
            System.setOut(printStream);
            System.setIn(spyInputStreamNot3Not5);
            assertEquals(myAnsw(45), 1);

            assertEquals("Ошибка. Начинай снова.", outStream.toString().trim());

            System.setOut(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Spy
    /* Создание спайа(spy-следит, что вызван метод с опр. аргументами) для System.in */
            ByteArrayInputStream inputNot3 = new ByteArrayInputStream("Any NOT Fizz".getBytes());
    InputStream spyInputStreamNot3 = spy(inputNot3);

    @Test
    void testMyAnsw3Err() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(out)
        ) {
            System.setOut(printStream);
            System.setIn(spyInputStreamNot3);
            assertEquals(myAnsw(3), 1);
            assertEquals("Ошибка. Начинай снова.", out.toString().trim());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Spy
    /* Создание спайа(spy-следит, что вызван метод с опр. аргументами) для System.in */
            ByteArrayInputStream inputNot5 = new ByteArrayInputStream("Any NOT Buzz".getBytes());
    InputStream spyInputStreamNot5 = spy(inputNot5);

    @Test
    void testMyAnsw5Err() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(out)
        ) {
            System.setOut(printStream);
            System.setIn(spyInputStreamNot5);
            assertEquals(myAnsw(5), 1);
            assertEquals("Ошибка. Начинай снова.", out.toString().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*            if (!String.valueOf(answer).equals(answer)) {
                  startAt = errore();
              } */
    @Spy
    /* Создание спайа(spy-следит, что вызван метод с опр. аргументами) для System.in */
            ByteArrayInputStream inputNot = new ByteArrayInputStream("13".getBytes());
    InputStream spyInputStreamNot = spy(inputNot);

    @Test
    void testMyAnswErr() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(out)
        ) {
            System.setOut(printStream);
            System.setIn(spyInputStreamNot);
            assertEquals(myAnsw(16), 17);
            assertEquals("", out.toString().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}