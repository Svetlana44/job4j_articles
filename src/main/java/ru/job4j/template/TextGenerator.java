package ru.job4j.template;

import java.util.Map;

public class TextGenerator implements Generator {
    @Override
    public String produce(String template, Map<String, String> args) {
        String result = "";
        if (!args.containsKey(template)) {
            throw new IllegalArgumentException();
        }
        return result;
    }
}
