package ru.job4j.ood.srp.model;

import ru.job4j.ood.srp.formatter.ReportDateTimeParser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
/*  Теперь класс WrapEmployee будет выводиться как <employee> в XML.  */

@XmlAccessorType(XmlAccessType.FIELD)
public class WrapEmployee {

    private String name;

    private String hired;
    private String fired;


    private double salary;

    public WrapEmployee() {
    }

    public WrapEmployee(Employee employee) {
        ReportDateTimeParser parserDate = new ReportDateTimeParser();
        this.hired = parserDate.parse(employee.getHired());
        this.fired = parserDate.parse(employee.getFired());

        this.name = employee.getName();
        this.salary = employee.getSalary();
    }

}
