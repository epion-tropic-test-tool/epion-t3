package com.epion_t3.excel.command.runner;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

public class Test {

    public static void main(String[] args) {

        File target = new File("");

        try (Workbook wb = WorkbookFactory.create(target);) {
            Iterator<Sheet> sheetIte = wb.sheetIterator();

            while (sheetIte.hasNext()) {
                Sheet sheet = sheetIte.next();
                System.out.println("Read Sheet. Name : " + sheet.getSheetName());

                Iterator<Row> rowIte = sheet.rowIterator();
                while (rowIte.hasNext()) {
                    Row row = rowIte.next();
                    Iterator<Cell> cellIte = row.cellIterator();
                    while (cellIte.hasNext()) {
                        Cell cell = cellIte.next();
                        if (cell != null) {

                            switch (cell.getCellType()) {
                                case STRING:
                                    String value = cell.getStringCellValue();
                                    value = value + "1";
                                    cell.setCellValue(value);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }
            try (OutputStream fileOut = new FileOutputStream(target)) {
                wb.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
