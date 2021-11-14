package eu.vargapeter.instrumentcatalog.services.impl;


import eu.vargapeter.instrumentcatalog.model.Teacher;
import eu.vargapeter.instrumentcatalog.repository.TeacherRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceImplTest {

    @InjectMocks
    TeacherServiceImpl teacherService;

    @Mock
    TeacherRepository teacherRepository;

    private static List<Teacher> teacherList = new ArrayList<>();

    @BeforeClass
    public static void init() {

        Teacher teacher1 = new Teacher();
        teacher1.setEducationalId(12345678910L);
        teacher1.setName("Minta Béla");
        teacher1.setIsActive(true);

        Teacher teacher2 = new Teacher();
        teacher2.setEducationalId(2345678912L);
        teacher2.setName("Minta Péter");
        teacher2.setIsActive(true);

        teacherList.add(teacher1);
        teacherList.add(teacher2);

    }

    @Test
    public void testGetAllActiveTeachers() {

        when(teacherRepository.findAllActiveTeachers()).thenReturn(teacherList);

        List<Teacher> teachers = teacherRepository.findAllActiveTeachers();
        assertEquals(2, teachers.size());
        assertEquals("Minta Béla", teachers.get(0).getName());
        verify(teacherRepository, times(1)).findAllActiveTeachers();

    }

    @Test
    public void testFindTeacherByEducationalId() {

        when(teacherRepository.findTeacherByEducationalId(2345678912L)).thenReturn(teacherList.get(1));

        Teacher teacher = teacherRepository.findTeacherByEducationalId(2345678912L);
        assertEquals("Minta Péter", teacher.getName());
        verify(teacherRepository, times(1)).findTeacherByEducationalId(2345678912L);

    }

}