package eu.vargapeter.instrumentcatalog.services;

import eu.vargapeter.instrumentcatalog.model.Rental;

import java.util.List;

public interface InstrumentRentalService {

    public List<Rental> getAllInstrumentRental();

    public void instrumentRent(Rental rental);

    public void endOfInstrumentRent(Integer id);

    public List<Rental> historyOfInstrumentRentals(Integer instrumentId);
}
