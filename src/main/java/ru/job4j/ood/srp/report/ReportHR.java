package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Comparator;
import java.util.function.Predicate;

/* Отдел HR попросил выводить сотрудников в порядке убывания зарплаты и убрать поля даты найма и увольнения. */
public class ReportHR implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportHR(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {

        Comparator<Employee> revertSort = (emp1, emp2) -> Double.compare(emp2.getSalary(), emp1.getSalary());

        store.sort(revertSort);

        StringBuilder text = new StringBuilder();
        text.append("Name; SalaryRevers;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
