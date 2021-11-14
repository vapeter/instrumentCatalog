package eu.vargapeter.instrumentcatalog.repository;

import eu.vargapeter.instrumentcatalog.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select s from Student s where s.educationalId = :educationalId")
    Student findStudentByEducationalId(@Param("educationalId") Long educationalId);

    @Query("select s from Student s where s.isActive = true")
    List<Student> findAllActiveStudents();

}
