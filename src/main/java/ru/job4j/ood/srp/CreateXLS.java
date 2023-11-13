package ru.job4j.ood.srp;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class CreateXLS {

    static File file;

    Sheet readXLS() {
        Sheet sheet = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Workbook workbook = WorkbookFactory.create(file);
            sheet = workbook.getSheetAt(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet;
    }

    void createXLS() {

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet in = readXLS();
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : in) {
            try {
                workbook.write((OutputStream) row);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
