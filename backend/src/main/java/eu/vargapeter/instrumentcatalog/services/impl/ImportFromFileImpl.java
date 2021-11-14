package eu.vargapeter.instrumentcatalog.services.impl;

import eu.vargapeter.instrumentcatalog.config.Translator;
import eu.vargapeter.instrumentcatalog.services.ExcelCheck;
import eu.vargapeter.instrumentcatalog.services.ImportFromFile;

import eu.vargapeter.instrumentcatalog.services.InstrumentsImport;
import eu.vargapeter.instrumentcatalog.services.StudentImport;
import eu.vargapeter.instrumentcatalog.services.TeacherImport;
import eu.vargapeter.instrumentcatalog.util.Constans;
import lombok.extern.slf4j.Slf4j;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

@Slf4j
@RequestScope
@Service
public class ImportFromFileImpl implements ImportFromFile {

    private ExcelCheck excelCheck;
    private StudentImport studentImport;
    private TeacherImport teacherImport;
    private InstrumentsImport instrumentsImport;

    @Autowired
    public ImportFromFileImpl(ExcelCheck excelCheck, StudentImport studentImport, TeacherImport teacherImport,
                              InstrumentsImport instrumentsImport) {
        this.excelCheck = excelCheck;
        this.studentImport = studentImport;
        this.teacherImport = teacherImport;
        this.instrumentsImport = instrumentsImport;
    }

    public static Integer DB_ROW = 0;
    public static Integer DB_NEW_ROW = 0;
    public static Integer DB_UPDATED_ROW = 0;


    @Override
    public void importFromExcel(String fileName, String importType) throws Exception {

        counterInit();

        try (
                FileInputStream excelFile = new FileInputStream(new File(Constans.IMPORT_FILE_URL + fileName));
                Workbook workbook = new XSSFWorkbook(excelFile)
        ) {
            Sheet sheet = workbook.getSheetAt(Constans.FIRST_ROW_IN_EXCEL);
            Iterator<Row> rowIterator = sheet.rowIterator();

            while (rowIterator.hasNext()) {

                Row currentRow = rowIterator.next();

                // file check
                if (currentRow.getRowNum() == Constans.FIRST_ROW_IN_EXCEL) {
                    excelCheck.firstRowCheck(currentRow, importType);
                    log.info(Translator.toLocale("import.check.success"));
                }

                if (currentRow.getRowNum() != Constans.FIRST_ROW_IN_EXCEL) {
                    if (importType.equals(Constans.STUDENT_IMPORT)) {
                        studentImport.studentRowReadFromExcel(currentRow);
                    } else if (importType.equals(Constans.TEACHER_IMPORT)) {
                        teacherImport.teacherRowReadFromExcel(currentRow);
                    } else if (importType.equals(Constans.INSTRUMENT_IMPORT)) {
                        instrumentsImport.instrumentReadFromExcel(currentRow);
                    }
                }

            }
            log.info(Translator.toLocale("import.check.successlog0")
                    + importType
                    + Translator.toLocale("import.check.successlog1")
                    + DB_ROW
                    + Translator.toLocale("import.check.successlog2")
                    + DB_NEW_ROW
                    + Translator.toLocale("import.check.successlog3")
                    + DB_UPDATED_ROW
                    + Translator.toLocale("import.check.successlog4")
            );


        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            throw new Exception();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new Exception();
        }

    }

    private void counterInit() {
        DB_ROW = 0;
        DB_NEW_ROW = 0;
        DB_UPDATED_ROW = 0;
    }

}
