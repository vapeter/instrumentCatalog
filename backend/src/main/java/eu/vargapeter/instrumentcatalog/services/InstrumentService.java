package eu.vargapeter.instrumentcatalog.services;

import eu.vargapeter.instrumentcatalog.model.Instrument;

import java.util.List;

public interface InstrumentService {

    public List<Instrument> getAllInstruments();

    public Instrument saveInstrument(Instrument instrument);

    public void deleteByInstrumentId(Integer instrumentId);

    public Instrument getOneById(Integer id);

    public void instrumentShowing(Integer instrumentId);

}
