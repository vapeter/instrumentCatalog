package eu.vargapeter.instrumentcatalog.services;

import org.apache.poi.ss.usermodel.Row;

public interface StudentImport {

    public void studentRowReadFromExcel(Row currentRow);
}
