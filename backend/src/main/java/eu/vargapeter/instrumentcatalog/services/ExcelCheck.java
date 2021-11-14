package eu.vargapeter.instrumentcatalog.services;

import org.apache.poi.ss.usermodel.Row;

public interface ExcelCheck {

    public void firstRowCheck(Row firstRow, String importType);
}
