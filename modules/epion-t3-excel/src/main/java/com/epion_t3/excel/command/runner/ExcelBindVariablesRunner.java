package com.epion_t3.excel.command.runner;

import com.epion_t3.excel.command.model.ExcelBindVariables;
import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

@Slf4j
public class ExcelBindVariablesRunner extends AbstractCommandRunner<ExcelBindVariables> {

    @Override
    public CommandResult execute(ExcelBindVariables command, Logger logger) throws Exception {

        // 対象のExcelファイルを解決
        Path targetFilePath = Paths.get(getCommandBelongScenarioDirectory(), command.getTarget()).normalize();

        try (Workbook wb = WorkbookFactory.create(targetFilePath.toFile());) {
            Iterator<Sheet> sheetIte = wb.sheetIterator();

            while (sheetIte.hasNext()) {
                Sheet sheet = sheetIte.next();
                log.debug("Read Sheet. Name : {}", sheet.getSheetName());

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

            try (OutputStream fileOut = new FileOutputStream(targetFilePath.toFile())) {
                wb.write(fileOut);
            }
        }
        return null;
    }

}
