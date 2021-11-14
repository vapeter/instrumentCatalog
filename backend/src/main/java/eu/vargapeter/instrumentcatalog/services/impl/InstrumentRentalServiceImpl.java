package eu.vargapeter.instrumentcatalog.services.impl;

import eu.vargapeter.instrumentcatalog.model.Instrument;
import eu.vargapeter.instrumentcatalog.model.Rental;
import eu.vargapeter.instrumentcatalog.repository.InstrumentRentalRepository;
import eu.vargapeter.instrumentcatalog.services.InstrumentRentalService;
import eu.vargapeter.instrumentcatalog.services.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class InstrumentRentalServiceImpl implements InstrumentRentalService {


    private InstrumentRentalRepository instrumentRentalRepository;

    private InstrumentService instrumentService;

    @Autowired
    public InstrumentRentalServiceImpl(InstrumentRentalRepository instrumentRentalRepository, InstrumentService instrumentService) {
        this.instrumentRentalRepository = instrumentRentalRepository;
        this.instrumentService = instrumentService;
    }

    @Override
    public List<Rental> getAllInstrumentRental() {
        return instrumentRentalRepository.findAll();
    }

    @Override
    @Transactional
    public void instrumentRent(Rental rental) {
        rental.getInstrument().setRented(true);
        instrumentService.saveInstrument(rental.getInstrument());
        instrumentRentalRepository.save(rental);
    }


    @Override
    @Transactional
    public void endOfInstrumentRent(Integer instrumentId) {

        Instrument rentedInstrument = instrumentService.getOneById(instrumentId);
        rentedInstrument.setRented(false);
        Rental rental = instrumentRentalRepository.getLastRentalOfInstrumentById(instrumentId);
        rental.setEndOfRental(LocalDate.now());
        instrumentRentalRepository.save(rental);
        instrumentService.saveInstrument(rentedInstrument);
    }

    @Override
    public List<Rental> historyOfInstrumentRentals(Integer instrumentId) {
        return instrumentRentalRepository.previousRentalsOfInstrument(instrumentId);
    }
}
