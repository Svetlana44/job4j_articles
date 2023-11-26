package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.tdd.Cinema3D;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportXMLTest extends Cinema3D {

    Calendar now = Calendar.getInstance();
    /* Устанавливаем нужный формат времени
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    String calendar = sdf.format(now.getTime()); */
    DateTimeParser<Calendar> parser = new ReportDateTimeParser();
    String calendar = parser.parse(now);

    @Test
    void generate() throws JAXBException {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportXML(store, parser);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employee>\n"
                + "    <name>Ivan</name>\n"
                + "    <hired>" + calendar + "</hired>\n"
                + "    <fired>" + calendar + "</fired>\n"
                + "    <salary>100.0</salary>\n"
                + "</employee>";
        assertThat(engine.generate(em -> true)).isEqualTo(expected);
    }
}