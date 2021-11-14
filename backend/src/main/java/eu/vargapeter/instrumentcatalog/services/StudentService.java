package eu.vargapeter.instrumentcatalog.services;

import eu.vargapeter.instrumentcatalog.model.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getAllActiveStudents();

    public void saveStudent(Student student);

    public List<Student> getAllStudents();

    public Student findStudentByEducationalId(Long educationalId);

    public void setActiveToFalse(Student student) throws Exception;

}
