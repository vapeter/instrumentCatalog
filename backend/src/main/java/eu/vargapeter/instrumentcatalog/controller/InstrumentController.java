package eu.vargapeter.instrumentcatalog.controller;

import eu.vargapeter.instrumentcatalog.config.ResponseHandler;
import eu.vargapeter.instrumentcatalog.config.Translator;
import eu.vargapeter.instrumentcatalog.model.Instrument;
import eu.vargapeter.instrumentcatalog.services.InstrumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class InstrumentController {

    private InstrumentService instrumentService;

    @Autowired
    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping("/instruments")
    public List<Instrument> getAllInstruments() {
        log.info(Translator.toLocale("admin.instrumentcontroller.instrumentList"));
        return instrumentService.getAllInstruments();
    }


    @PostMapping("/instruments")
    public ResponseEntity createNewInstrument(@Valid @RequestBody Instrument instrument, BindingResult result) {

        if (result.hasErrors()) {
            log.error(result.toString());
            return ResponseHandler.generateResponse(Translator.toLocale("admin.instrumentcontroller.badData"),
                    HttpStatus.BAD_REQUEST, null);
        }
        try {
            instrumentService.saveInstrument(instrument);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.instrumentcontroller.newInstrument"),
                    HttpStatus.CREATED, instrument);
        } catch (Exception e) {
            log.error(Translator.toLocale("admin.instrumentcontroller.error") + " " + e);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.instrumentcontroller.error"),
                    HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


    @PostMapping(value = "/instruments/delete/{id}")
    public ResponseEntity deleteInstrumentById(@PathVariable("id") Integer id) {

        log.debug(Translator.toLocale("admin.instrumentcontroller.deleteId") + id);

        try {
            instrumentService.deleteByInstrumentId(id);
            log.debug(Translator.toLocale("admin.instrumentcontroller.deletedInstrument") + id);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.instrumentcontroller.deletedInstrument"),
                    HttpStatus.OK, null);
        } catch (Exception e) {
            log.error(Translator.toLocale("admin.instrumentcontroller.missingInstrument") + ", " + e);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.instrumentcontroller.missingInstrument"),
                    HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("instruments/showing/{id}")
    public ResponseEntity instrumentShow(@PathVariable("id") Integer id) {

        try {
            instrumentService.instrumentShowing(id);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.instrumentcontroller.showingSuccess"),
                    HttpStatus.OK, null);
        } catch (Exception e) {
            log.error(Translator.toLocale("admin.instrumentcontroller.missingInstrument") + ", " + e);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.instrumentcontroller.missingInstrument"),
                    HttpStatus.BAD_REQUEST, null);
        }
    }
}
