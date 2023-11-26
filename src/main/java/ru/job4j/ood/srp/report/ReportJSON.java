package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.WrapEmployee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportJSON implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportJSON(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {

        StringBuilder text = new StringBuilder();

        text.append("[");
        for (Employee employee : store.findBy(filter)) {
            /* Преобразуем объект person в json-строку. */
            final Gson gson = new GsonBuilder().create();
            text.append(gson.toJson(new WrapEmployee(employee)));
            text.append(",");
        }
        text.deleteCharAt(text.length() - 1);
        text.append("]");
        return text.toString();
    }
}
/*
[
    {
        "name": "John Doe",
        "hired": "08:06:2023 17:41",
        "fired": "08:06:2023 17:41",
        "salsry": 5000.0
    },
...
]
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
**/