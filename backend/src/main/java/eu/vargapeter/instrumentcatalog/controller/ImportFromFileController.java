package eu.vargapeter.instrumentcatalog.controller;

import eu.vargapeter.instrumentcatalog.config.ResponseHandler;
import eu.vargapeter.instrumentcatalog.config.Translator;
import eu.vargapeter.instrumentcatalog.services.ImportFromFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImportFromFileController {

    private ImportFromFile importFromFile;

    @Autowired
    public ImportFromFileController(ImportFromFile importFromFile) {
        this.importFromFile = importFromFile;
    }

    @PostMapping("importFromFile/{fileName}/{importType}")
    public ResponseEntity importFromFile(@PathVariable("fileName") String fileName,
                                         @PathVariable("importType") String importType) {

        try {
            importFromFile.importFromExcel(fileName, importType);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.import.success"),
                    HttpStatus.OK, null);
        } catch (Exception e) {
            log.error(Translator.toLocale("admin.import.fault") + " " + e);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.import.fault"),
                    HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
