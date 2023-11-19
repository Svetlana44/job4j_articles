package ru.job4j.ood.srp.store;

import ru.job4j.ood.srp.model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStore implements Store {

    public List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> sort(Comparator<Employee> employeeComparator) {
        employees.sort(employeeComparator);
        return employees;
    }

    @Override
    public void add(Employee em) {
        employees.add(em);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }
}