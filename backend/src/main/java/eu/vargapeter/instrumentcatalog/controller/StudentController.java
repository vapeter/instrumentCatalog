package eu.vargapeter.instrumentcatalog.controller;

import eu.vargapeter.instrumentcatalog.config.ResponseHandler;
import eu.vargapeter.instrumentcatalog.config.Translator;
import eu.vargapeter.instrumentcatalog.model.Student;
import eu.vargapeter.instrumentcatalog.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("students")
    public List<Student> getAllActiveStudents() {
        log.info(Translator.toLocale("admin.studentcontroller.studentList"));
        return studentService.getAllActiveStudents();
    }

    @PostMapping("students/quit")
    public ResponseEntity quit(@Valid @RequestBody Student student, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseHandler.generateResponse(Translator.toLocale("admin.studentcontroller.badData"),
                    HttpStatus.BAD_REQUEST, null);
        }

        try {
            studentService.setActiveToFalse(student);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.studentcontroller.quitStudent"),
                    HttpStatus.OK, student);
        } catch (Exception e) {
            log.error(Translator.toLocale("admin.studentcontroller.missingStudent") + " " + e);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.studentcontroller.missingStudent"),
                    HttpStatus.BAD_REQUEST, null);
        }

    }

}
