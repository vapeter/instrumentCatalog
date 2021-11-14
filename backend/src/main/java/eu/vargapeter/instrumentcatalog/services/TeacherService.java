package eu.vargapeter.instrumentcatalog.services;

import eu.vargapeter.instrumentcatalog.model.Teacher;

import java.util.List;

public interface TeacherService {

    public void saveTeacher(Teacher teacher);

    public Teacher findTeacherByEducationalId(Long educationalId);

    public List<Teacher> getAllActiveTeachers();

    public void setActiveToFalse(Teacher teacher);


}
