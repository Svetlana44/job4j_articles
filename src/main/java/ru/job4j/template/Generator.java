package ru.job4j.template;

import java.util.Map;

/*   Генератор получает шаблон вида.
 "I am a ${name}, Who are ${subject}? "
Слова записанные в фигурных скобках ${..} - это ключи, которые нужно заменить.
В генератор передаются значения этих ключей в виде пары ключ - значение.
  */
public interface Generator {
    String produce(String template, Map<String, String> args);
}
