package ru.job4j.template;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
/*  Генератор получает шаблон вида.
"I am a ${name}, Who are ${subject}? "
Слова записанные в фигурных скобках ${..} - это ключи, которые нужно заменить.
В генератор передаются значения этих ключей в виде пары ключ - значение.
Например:
name -> Petr Arsentev
subject -> you
Генератор должен вернуть строку
"I am a Petr Arsentev, Who are you? "
2. Программа должна учитывать, что в шаблоне есть ключи, которых нет в карте (Map) и кидать исключение.
3. Программа должна учитывать, что в карте есть лишние ключи и тоже кидать исключение.*/

class TextGeneratorTest {

    Generator generator = new TextGenerator();
    static Map<String, String> args = new HashMap<>();

    @Disabled /* тесты отключены до полного написания кода */
    @BeforeAll
    static void addKeys() {
        args.put("name", "Tim Krus");
        args.put("subject", "you");
    }
    @Disabled /* тесты отключены до полного написания кода */
    @Test
    void produceTwoKeysExists() {
        String in = "I am a ${name}, Who are ${subject}? ";
        String expected = "I am a Petr Arsentev, Who are you? ";
        String actual = generator.produce(in, args);
        Assertions.assertThat(actual).isEqualTo(expected);
    }
    @Disabled /* тесты отключены до полного написания кода */
    @Test
    void produceTwoKeysNotExists() {
        String in = "I am a ${name}, Who are ${subject}? ${hello}";

        Assertions.assertThatThrownBy(() -> generator.produce(in, args)).isInstanceOf(IllegalArgumentException.class);
        /*     .hasMessageContaining("boom"); */
    }
    @Disabled /* тесты отключены до полного написания кода */
    /* лишние ключи */
    @Test
    void produceTwoKeysOver() {
        Map<String, String> argsOver = new HashMap<>();
        String in = "I am a ${name}, Who are ${subject}? ";
        argsOver.put("OverKey", "OverValue");
        args.put("name", "Tim Krus");
        args.put("subject", "you");

        Assertions.assertThatThrownBy(() -> generator.produce(in, argsOver)).isInstanceOf(IllegalArgumentException.class);
    }
}