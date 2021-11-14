package eu.vargapeter.instrumentcatalog.controller;

import eu.vargapeter.instrumentcatalog.config.ResponseHandler;
import eu.vargapeter.instrumentcatalog.config.Translator;
import eu.vargapeter.instrumentcatalog.model.Teacher;
import eu.vargapeter.instrumentcatalog.services.TeacherService;
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
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("teachers")
    public List<Teacher> getAllTeacher() {
        log.info(Translator.toLocale("admin.teachercontroller.teacherList"));
        return teacherService.getAllActiveTeachers();
    }

    @PostMapping("/teachers/quit")
    public ResponseEntity quit(@Valid @RequestBody Teacher teacher, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseHandler.generateResponse(Translator.toLocale("admin.teachercontroller.badData"),
                    HttpStatus.BAD_REQUEST, null);
        }

        try {
            teacherService.setActiveToFalse(teacher);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.teachercontroller.quitTeacher"),
                    HttpStatus.OK, teacher);
        } catch (Exception e) {
            log.error(Translator.toLocale("admin.teachercontroller.missingTeacher") + " " + e);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.teachercontroller.missingTeacher"),
                    HttpStatus.BAD_REQUEST, null);
        }
    }
}
