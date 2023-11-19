package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.function.Predicate;

/* Для отдела программистов потребовался отчет в формате CSV
 (с данным форматом вы хорошо познакомились в задании Scanner).
  Отчет нужно изобразить в данном формате с помощью строк.   */
public class ReportDEV implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportDEV(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {

        StringBuilder text = new StringBuilder();

        File file = new File("ReportDEV.csv");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(file))) {
            text.append("Name; Hired; Fired; Salary;")
                    .append(System.lineSeparator());
            outFile.write(text.toString());

            for (Employee employee : store.findBy(filter)) {
                String addStr = String.format("%s %s %s %s",
                        employee.getName(),
                        dateTimeParser.parse(employee.getHired()),
                        dateTimeParser.parse(employee.getFired()),
                        employee.getSalary()
                )
                        + System.lineSeparator();
                outFile.write(addStr);
                text.append(addStr);
/*                text.append(employee.getName()).append(" ")
                        .append(dateTimeParser.parse(employee.getHired())).append(" ")
                        .append(dateTimeParser.parse(employee.getFired())).append(" ")
                        .append(employee.getSalary())
                        .append(System.lineSeparator());  */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}
