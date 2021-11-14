package eu.vargapeter.instrumentcatalog.controller;

import eu.vargapeter.instrumentcatalog.config.ResponseHandler;
import eu.vargapeter.instrumentcatalog.config.Translator;
import eu.vargapeter.instrumentcatalog.services.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@Slf4j
@RestController
@RequestMapping(value = "/admin")
public class FileUploadController {

    FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file")MultipartFile file) {

        try {
            log.debug(Translator.toLocale("admin.uploadcontroller.uploadedfile") + " "
                    + file.getOriginalFilename());
            fileUploadService.storeFile(file);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.uploadcontroller.success"),
                    HttpStatus.OK, null);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseHandler.generateResponse(Translator.toLocale("admin.uploadcontroller.error"),
                    HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
