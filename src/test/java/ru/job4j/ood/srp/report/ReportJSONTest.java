package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
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

    Calendar now = Calendar.getInstance();
    Gson gson = new Gson();
    String calendar = gson.toJson(now);


    @Test
    void calendarToJson() {
        String expected = "{\"year\":" + now.get(Calendar.YEAR)
                + ",\"month\":" + now.get(Calendar.MONTH)
                + ",\"dayOfMonth\":" + now.get(Calendar.DAY_OF_MONTH)
                + ",\"hourOfDay\":" + now.get(Calendar.HOUR_OF_DAY)
                + ",\"minute\":" + now.get(Calendar.MINUTE)
                + ",\"second\":" + now.get(Calendar.SECOND) + "}";
        assertThat(expected).isEqualTo(calendar);
    }

    @Test
    void generate() throws JAXBException {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportJSON(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("[{")
                .append("\"name\":\"")
                .append(worker.getName()).append("\",")
                .append("\"hired\":")
                .append(calendar).append(",")
                .append("\"fired\":")
                .append(calendar).append(",")
                .append("\"salary\":")
                .append(worker.getSalary())
                .append("}]");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}
/*
but was:
"[{"name":"Ivan","hired":{"year":2023,"month":10,"dayOfMonth":22,"hourOfDay":20,"minute":39,"second":36},"fired":{"year":2023,"month":10,"dayOfMonth":22,"hourOfDay":20,"minute":39,"second":36},"salary":100.0},]"
*/