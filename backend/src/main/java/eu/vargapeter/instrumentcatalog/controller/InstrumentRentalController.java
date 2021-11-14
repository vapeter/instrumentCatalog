package eu.vargapeter.instrumentcatalog.controller;

import eu.vargapeter.instrumentcatalog.config.ResponseHandler;
import eu.vargapeter.instrumentcatalog.config.Translator;
import eu.vargapeter.instrumentcatalog.model.Rental;
import eu.vargapeter.instrumentcatalog.services.InstrumentRentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class InstrumentRentalController {

    private InstrumentRentalService instrumentRentalService;

    @Autowired
    public InstrumentRentalController(InstrumentRentalService instrumentRentalService) {
        this.instrumentRentalService = instrumentRentalService;
    }

    @PostMapping("rent")
    public ResponseEntity instrumentRent(@Valid @RequestBody Rental rental, BindingResult result) {

        if(result.hasErrors()) {
            log.error(result.toString());
            return ResponseHandler.generateResponse(Translator.toLocale("admin.rent.badData"),
                    HttpStatus.BAD_REQUEST, null);
        }

        try {
            instrumentRentalService.instrumentRent(rental);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.rent.success"),
                    HttpStatus.OK, rental);
        } catch (Exception e) {
            log.error(Translator.toLocale("admin.rent.failed") + " " + e);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.rent.failed"),
                    HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("rent/backToStorage/{id}")
    public ResponseEntity backToStorage(@PathVariable("id") String id) {

        log.debug(Translator.toLocale("admin.rent.backToStorageId") + id);

        try {
            instrumentRentalService.endOfInstrumentRent(Integer.parseInt(id));
            return ResponseHandler.generateResponse(Translator.toLocale("admin.rent.backToStorage"),
                    HttpStatus.OK, null);
        } catch (Exception e) {
            log.error(Translator.toLocale("admin.rent.backToStorageFailed") + " " + e);
            return ResponseHandler.generateResponse(Translator.toLocale("admin.rent.backToStorageFailed"),
                    HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("historyOfInstrumentRentals/{id}")
    public List<Rental> historyOfInstrumentRentals(@PathVariable("id") String id) {
        return instrumentRentalService.historyOfInstrumentRentals(Integer.parseInt(id));
    }
}
