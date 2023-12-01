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

class ReportJSONTest extends Cinema3D {

    ReportDateTimeParser parser = new ReportDateTimeParser();

    Calendar now = Calendar.getInstance();
    String calendar = parser.parse(now);

    @Test
    void calendarToJson() {
        String expected = String.format("%02d", now.get(Calendar.DAY_OF_MONTH))  /* форматирует что две цифры, если одна, то добавляет ведущий ноль */
                + ":" + String.format("%02d", (now.get(Calendar.MONTH) + 1))
                + ":" + now.get(Calendar.YEAR)
                + " " + String.format("%02d", now.get(Calendar.HOUR_OF_DAY))
                + ":" + String.format("%02d", now.get(Calendar.MINUTE));  /* String.format("%02d"  */
        assertThat(expected).isEqualTo(calendar);
    }

    @Test
    void generate() throws JAXBException {
        Calendar now2 = (Calendar) now.clone();
        now2.add(Calendar.MINUTE, 1);
        String calendar2 = parser.parse(now2);

        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Ivan2", now2, now2, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportJSON(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("[{")
                .append("\"name\":\"")
                .append(worker.getName()).append("\",")
                .append("\"hired\":\"")
                .append(calendar).append("\",")
                .append("\"fired\":\"")
                .append(calendar).append("\",")
                .append("\"salary\":")
                .append(worker.getSalary())
                .append("},")
                .append("{")
                .append("\"name\":\"")
                .append(worker2.getName()).append("\",")
                .append("\"hired\":\"")
                .append(calendar2).append("\",")
                .append("\"fired\":\"")
                .append(calendar2).append("\",")
                .append("\"salary\":")
                .append(worker2.getSalary())
                .append("}")
                .append("]");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}
/*
but was:
"[{"name":"Ivan","hired":{"year":2023,"month":10,"dayOfMonth":22,"hourOfDay":20,"minute":39,"second":36},"fired":{"year":2023,"month":10,"dayOfMonth":22,"hourOfDay":20,"minute":39,"second":36},"salary":100.0},]"
*/