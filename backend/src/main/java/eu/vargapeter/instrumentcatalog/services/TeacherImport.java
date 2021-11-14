package eu.vargapeter.instrumentcatalog.services;

import org.apache.poi.ss.usermodel.Row;

public interface TeacherImport {

    public void teacherRowReadFromExcel(Row currentRow);
}
