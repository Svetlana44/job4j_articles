package ru.job4j.articles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.articles.service.SimpleArticleService;
import ru.job4j.articles.service.generator.RandomArticleGenerator;
import ru.job4j.articles.store.ArticleStore;
import ru.job4j.articles.store.WordStore;

import java.io.InputStream;
import java.util.Properties;

public class Application {

    private final Logger lOGGER = LoggerFactory.getLogger(Application.class.getSimpleName());

    public final int tARGETcOUNT = 1_000_000;

    public static void main(String[] args) {
        Application app = new Application();

        var properties = app.loadProperties();
        var wordStore = new WordStore(properties);
        var articleStore = new ArticleStore(properties);
        var articleGenerator = new RandomArticleGenerator();
        var articleService = new SimpleArticleService(articleGenerator);


        articleService.generate(wordStore, app.tARGETcOUNT, articleStore);
    }

    private Properties loadProperties() {
        lOGGER.info("Загрузка настроек приложения");
        var properties = new Properties();
        try (InputStream in = Application.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(in);
        } catch (Exception e) {
            lOGGER.error("Не удалось загрузить настройки. { }", e.getCause());
            throw new IllegalStateException();
        }
        return properties;
    }

}
