package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

/* В бухгалтерском отчете необходимо сделать конвертацию зарплаты в одну из валют.
 Для этого был реализован конвертер валют  InMemoryCurrencyConverter() */
public class ReportBuhgalter implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportBuhgalter(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        /*   public double convert(Currency source, double sourceValue, Currency target) {    */
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        Currency sourse = Currency.EUR;

        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; SalaryConvert;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(converter.convert(sourse, employee.getSalary(), Currency.USD))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
