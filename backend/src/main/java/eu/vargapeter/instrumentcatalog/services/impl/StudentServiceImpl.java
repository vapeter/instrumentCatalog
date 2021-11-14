package eu.vargapeter.instrumentcatalog.services.impl;

import eu.vargapeter.instrumentcatalog.model.Student;
import eu.vargapeter.instrumentcatalog.repository.StudentRepository;
import eu.vargapeter.instrumentcatalog.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllActiveStudents() {
        return studentRepository.findAllActiveStudents();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findStudentByEducationalId(Long educationalId) {
        return studentRepository.findStudentByEducationalId(educationalId);
    }

    @Override
    public void setActiveToFalse(Student student) throws Exception {

        Student studentByEducationalId =
                    this.findStudentByEducationalId(student.getEducationalId());
        studentByEducationalId.setIsActive(false);

        this.saveStudent(studentByEducationalId);
    }
}
