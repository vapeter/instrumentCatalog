package eu.vargapeter.instrumentcatalog.services.impl.imports;

import eu.vargapeter.instrumentcatalog.config.Translator;
import eu.vargapeter.instrumentcatalog.exceptions.InvalidExcelCellException;
import eu.vargapeter.instrumentcatalog.model.Gender;
import eu.vargapeter.instrumentcatalog.model.Student;
import eu.vargapeter.instrumentcatalog.services.StudentImport;
import eu.vargapeter.instrumentcatalog.services.impl.ImportFromFileImpl;
import eu.vargapeter.instrumentcatalog.services.StudentService;
import eu.vargapeter.instrumentcatalog.util.Constans;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentImportImpl implements StudentImport {


    private StudentService studentService;

    @Autowired
    public StudentImportImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    private Long educationalId;
    private String name;
    private String birthName;
    private String mothersName;
    private LocalDate birthDate;
    private String birthPlace;
    private String gender;

    private Student student = null;

    @Override
    public void studentRowReadFromExcel(Row currentRow) {

        ImportFromFileImpl.DB_ROW++;

        try {
            educationalId = Long.parseLong(currentRow.getCell(Constans.IMPORT_STUDENT_EDUCATIONAL_ID_CELL)
                    .getStringCellValue());

            // name
            if (currentRow.getCell(Constans.IMPORT_STUDENT_NAME_PREFIX_CELL) != null) {
                name = currentRow.getCell(Constans.IMPORT_STUDENT_NAME_PREFIX_CELL).getStringCellValue() + " " +
                        currentRow.getCell(Constans.IMPORT_STUDENT_FIRST_NAME_CELL).getStringCellValue() + " " +
                        currentRow.getCell(Constans.IMPORT_STUDENT_LAST_NAME_CELL).getStringCellValue();
            } else {
                name = currentRow.getCell(Constans.IMPORT_STUDENT_FIRST_NAME_CELL).getStringCellValue() + " " +
                        currentRow.getCell(Constans.IMPORT_STUDENT_LAST_NAME_CELL).getStringCellValue();
            }

            // birth name
            if (currentRow.getCell(Constans.IMPORT_STUDENT_BIRTH_NAME_PREFIX_CELL) != null) {
                birthName = currentRow.getCell(Constans.IMPORT_STUDENT_BIRTH_NAME_PREFIX_CELL).getStringCellValue() + " " +
                        currentRow.getCell(Constans.IMPORT_STUDENT_BIRTH_FIRST_NAME_CELL).getStringCellValue() + " " +
                        currentRow.getCell(Constans.IMPORT_STUDENT_BIRTH_LAST_NAME_CELL).getStringCellValue();
            } else {
                birthName = currentRow.getCell(Constans.IMPORT_STUDENT_BIRTH_FIRST_NAME_CELL).getStringCellValue() + " " +
                        currentRow.getCell(Constans.IMPORT_STUDENT_BIRTH_LAST_NAME_CELL).getStringCellValue();
            }

            // mothers name
            if (currentRow.getCell(Constans.IMPORT_STUDENT_MOTHERS_NAME_PREFIX_CELL) != null) {
                mothersName = currentRow.getCell( Constans.IMPORT_STUDENT_MOTHERS_NAME_PREFIX_CELL).getStringCellValue() + " " +
                        currentRow.getCell(Constans.IMPORT_STUDENT_MOTHERS_FIRST_NAME_CELL).getStringCellValue() + " " +
                        currentRow.getCell(Constans.IMPORT_STUDENT_MOTHERS_LAST_NAME_CELL).getStringCellValue();
            } else {
                mothersName = currentRow.getCell(Constans.IMPORT_STUDENT_MOTHERS_FIRST_NAME_CELL).getStringCellValue() + " " +
                        currentRow.getCell(Constans.IMPORT_STUDENT_MOTHERS_LAST_NAME_CELL).getStringCellValue();
            }

            birthDate = LocalDate.parse(currentRow.getCell(Constans.IMPORT_STUDENT_BIRTH_DATE_CELL).getStringCellValue(),
                    Constans.STUDENT_IMPORT_FROM_FILE_DATE_PATTERN);

            birthPlace = currentRow.getCell(Constans.IMPORT_STUDENT_BIRTH_PLACE_CELL).getStringCellValue();

            // gender
            gender = currentRow.getCell(Constans.IMPORT_STUDENT_GENDER_CELL).getStringCellValue();


        } catch (Exception e) {
            throw new InvalidExcelCellException(Translator.toLocale("import.wrongfile") + e.getMessage());
        }

        saveStudent();
    }

    private void saveStudent() {

        if (studentService.findStudentByEducationalId(educationalId) != null) {
            student = studentService.findStudentByEducationalId(educationalId);
            ImportFromFileImpl.DB_UPDATED_ROW++;
        } else {
            student = new Student();
            ImportFromFileImpl.DB_NEW_ROW++;
        }

        student.setEducationalId(educationalId);
        student.setName(name.trim());
        student.setBirthName(birthName.trim());
        student.setMothersName(mothersName.trim());
        student.setBirthDate(birthDate);
        student.setBirthPlace(birthPlace);
        student.setIsActive(true);

        if (gender.equals(Constans.IMPORT_STUDENT_GENDER_WOMEN_CELL_DATA)) {
            student.setGender(Gender.GIRL);
        } else {
            student.setGender(Gender.BOY);
        }

        studentService.saveStudent(student);

    }

}
