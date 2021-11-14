package eu.vargapeter.instrumentcatalog.services.impl.imports;

import eu.vargapeter.instrumentcatalog.config.Translator;
import eu.vargapeter.instrumentcatalog.exceptions.InvalidFileImportException;
import eu.vargapeter.instrumentcatalog.services.ExcelCheck;
import eu.vargapeter.instrumentcatalog.util.Constans;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

@Service
public class ExcelCheckImpl implements ExcelCheck {

    public void firstRowCheck(Row firstRow, String importType) {

        if (importType.equals(Constans.STUDENT_IMPORT)) {
            studentTypeCheck(firstRow);
        } else if (importType.equals(Constans.TEACHER_IMPORT)) {
            teacherTypeCheck(firstRow);
        } else if (importType.equals(Constans.INSTRUMENT_IMPORT)) {
            instrumentTypeCheck(firstRow);
        }
    }

    private void studentTypeCheck(Row firstRow) {

       Short numberOfColumns = firstRow.getLastCellNum();

       for (int i = 0; i < numberOfColumns; i++) {
           String columnHeader = firstRow.getCell(i).getStringCellValue();
           if (!Constans.STUDENT_EXCEL_COLUMNS.contains(columnHeader)) {
               excelCheckFault(i + 1, Constans.STUDENT_IMPORT);
           }
       }
    }

    private void teacherTypeCheck(Row firstRow) {

        Short numberOfColumns = firstRow.getLastCellNum();

        for (int i = 0; i < numberOfColumns; i++) {
            String columnHeader = firstRow.getCell(i).getStringCellValue();
            if (!Constans.TEACHER_EXCEL_COLUMNS.contains(columnHeader)) {
                excelCheckFault(i + 1, Constans.STUDENT_IMPORT);
            }
        }
    }

    private void instrumentTypeCheck(Row firstRow) {

        Short numberOfColumns = firstRow.getLastCellNum();

        for (int i = 0; i < numberOfColumns; i++) {
            String columnHeader = firstRow.getCell(i).getStringCellValue();
            if (!Constans.INSTRUMENTS_EXCEL_COLUMNS.contains(columnHeader)) {
                excelCheckFault(i + 1, Constans.STUDENT_IMPORT);
            }
        }
    }

    private void excelCheckFault(Integer cell, String importType) {
        throw new InvalidFileImportException(
                Translator.toLocale("import.excelfile.wrongformat")
                        + importType
                        + " "
                        + Translator.toLocale("import.invalid.column")
                        + " "
                        + cell
        );
    }
}
