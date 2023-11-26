package ru.job4j.ood.srp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement

public class Employee {

    private String name;
/*    @XmlJavaTypeAdapter(EmployeeAdapter.class) /* чтобы использовался свой адаптер для сериализации/десериализации */
    private Calendar hired;
/*    @XmlJavaTypeAdapter(EmployeeAdapter.class) /* чтобы использовался свой адаптер для сериализации/десериализации */
    private Calendar fired;

    private double salary;

    public Employee() {
    }

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public Employee clone() {
        Employee employeeClone = this.clone();
        Calendar hiredClone = (Calendar) this.hired.clone();
        employeeClone.setHired(hiredClone);
        Calendar firedClone = (Calendar) this.fired.clone();
        employeeClone.setHired(firedClone);
        return employeeClone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /* Для изменения сериализации для класса Employee,
     необходимо создать класс-адаптер или использовать аннотации для указания специфических правил сериализации.
     Затем необходимо зарегистрировать адаптер или аннотации в контексте JAXB перед выполнением маршалинга в методе generate:
     JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class, EmployeeAdapter.class); */
    /* не используется из-за непонятной ошибки
    public class EmployeeAdapter extends XmlAdapter<String, Calendar> {
        @Override
        public Calendar unmarshal(String empl) throws Exception {
            return Calendar.getInstance();
        }

        @Override
        public String marshal(Calendar calendar) throws Exception {
          /*  ReportDateTimeParser parserDate = new ReportDateTimeParser();
            String hired = parserDate.parse(employee.getHired());
            String fired = parserDate.parse(employee.getFired());  */
            /* Устанавливаем нужный формат времени
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            String rsl = sdf.format(calendar.getTime());

            return rsl;
        }
    }*/
}
