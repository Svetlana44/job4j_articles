package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.WrapEmployee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportXML implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportXML(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(WrapEmployee.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringBuilder text = new StringBuilder();
/*   создают новый объект StringWriter, и затем используют marshaller для маршалинга (преобразования в строку) объекта employee
 и записи этой строки в объект StringWriter
  WrapEmployee это обёртка класса для перевода полей hired,fired в форматированный String
  */
        for (Employee employee : store.findBy(filter)) {
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new WrapEmployee(employee), writer);
                text.append(writer.getBuffer());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        text.deleteCharAt(text.length() - 1);
        return text.toString();

    }
}
/*Объект StringWriter в Java используется для записи символов в строку, вместо записи в файл или другой выходной поток данных.
 Он полезен, когда требуется собрать и сохранить в памяти большой объем текстовых данных,
  например, при формировании XML или JSON документов.
 StringWriter также может быть использован для временного хранения данных перед их записью в файл или передачей по сети.

 Для использования своего адаптера сериализации для класса Employee,
  необходимо создать класс-адаптер EmployeeAdapter, который расширяет XmlAdapter<String, Employee>.
В методе marshal() этого адаптера необходимо реализовать логику преобразования
 объекта Employee в строку XML с использованием нужного формата времени для полей hired и fired.
После этого необходимо добавить аннотацию @XmlJavaTypeAdapter(Employee.EmployeeAdapter.class)
к классу Employee, чтобы указать, что используется свой адаптер для сериализации/десериализации.
И наконец, при выполнении маршалинга объекта Employee нужно зарегистрировать адаптер в контексте JAXB, например:
JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class, EmployeeAdapter.class);
Это позволит использовать свой адаптер при преобразовании объекта Employee в XML и обратно.*/