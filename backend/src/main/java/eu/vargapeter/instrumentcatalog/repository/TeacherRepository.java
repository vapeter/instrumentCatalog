package eu.vargapeter.instrumentcatalog.repository;

import eu.vargapeter.instrumentcatalog.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("select t from Teacher t where t.educationalId = :educationalId")
    Teacher findTeacherByEducationalId(@Param("educationalId") Long educationalId);

    @Query("select t from Teacher t where t.isActive = true")
    List<Teacher> findAllActiveTeachers();
}
