package eu.vargapeter.instrumentcatalog.services.impl.imports;

import eu.vargapeter.instrumentcatalog.config.Translator;
import eu.vargapeter.instrumentcatalog.exceptions.InvalidExcelCellException;
import eu.vargapeter.instrumentcatalog.model.Teacher;
import eu.vargapeter.instrumentcatalog.services.TeacherImport;
import eu.vargapeter.instrumentcatalog.services.impl.ImportFromFileImpl;
import eu.vargapeter.instrumentcatalog.services.TeacherService;
import eu.vargapeter.instrumentcatalog.util.Constans;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherImportImpl implements TeacherImport {


    private TeacherService teacherService;

    @Autowired
    public TeacherImportImpl(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    private Long educationalId;
    private String name;

    private Teacher teacher = null;

    @Override
    public void teacherRowReadFromExcel(Row currentRow) {

        ImportFromFileImpl.DB_ROW++;

        try {
            educationalId = Long.parseLong(
                    currentRow.getCell(Constans.IMPORT_TECHER_EDUCATIONAL_ID_CELL)
                            .getStringCellValue());

            // Name
            if (currentRow.getCell(Constans.IMPORT_TECHER_NAME_PREFIX) != null) {
                name =   currentRow.getCell(Constans.IMPORT_TECHER_NAME_PREFIX).getStringCellValue() + " " +
                        currentRow.getCell(Constans.IMPORT_TECHER_FIRST_NAME).getStringCellValue() + " " +
                        currentRow.getCell(Constans.IMPORT_TECHER_LAST_NAME).getStringCellValue();
            } else {
                name =   currentRow.getCell(Constans.IMPORT_TECHER_FIRST_NAME).getStringCellValue() + " " +
                        currentRow.getCell(Constans.IMPORT_TECHER_LAST_NAME).getStringCellValue();
            }

        } catch (Exception e) {
            throw new InvalidExcelCellException(Translator.toLocale("import.wrongfile") + e.getMessage());
        }

        saveTeacher();
    }

    private void saveTeacher() {

        if (teacherService.findTeacherByEducationalId(educationalId) != null) {
            teacher = teacherService.findTeacherByEducationalId(educationalId);
            ImportFromFileImpl.DB_UPDATED_ROW++;
        } else {
            teacher = new Teacher();
            ImportFromFileImpl.DB_NEW_ROW++;
        }

        teacher.setIsActive(true);
        teacher.setEducationalId(educationalId);
        teacher.setName(name.trim());

        teacherService.saveTeacher(teacher);
    }
}
