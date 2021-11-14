package eu.vargapeter.instrumentcatalog.services.impl;

import eu.vargapeter.instrumentcatalog.model.Teacher;
import eu.vargapeter.instrumentcatalog.repository.TeacherRepository;
import eu.vargapeter.instrumentcatalog.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllActiveTeachers() {
        return teacherRepository.findAllActiveTeachers();
    }


    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher findTeacherByEducationalId(Long educationalId) {
        return teacherRepository.findTeacherByEducationalId(educationalId);
    }

    @Override
    public void setActiveToFalse(Teacher teacher) {
        Teacher teacherByEducationalId =
                this.findTeacherByEducationalId(teacher.getEducationalId());
        teacherByEducationalId.setIsActive(false);
        this.saveTeacher(teacherByEducationalId);
    }

}
