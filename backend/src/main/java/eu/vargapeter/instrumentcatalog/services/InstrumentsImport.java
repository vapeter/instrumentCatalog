package eu.vargapeter.instrumentcatalog.services;

import org.apache.poi.ss.usermodel.Row;

public interface InstrumentsImport {

    public void instrumentReadFromExcel(Row currentRow);
}
