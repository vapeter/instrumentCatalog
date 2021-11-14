package eu.vargapeter.instrumentcatalog.services.impl;

import eu.vargapeter.instrumentcatalog.model.Instrument;
import eu.vargapeter.instrumentcatalog.repository.InstrumentRepository;
import eu.vargapeter.instrumentcatalog.services.InstrumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class InstrumentServiceImpl implements InstrumentService {


    private InstrumentRepository instrumentRepository;

    @Autowired
    public InstrumentServiceImpl(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public List<Instrument> getAllInstruments() {
        return instrumentRepository.findAll();
    }

    @Override
    public Instrument saveInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }


    @Override
    @Transactional
    public void deleteByInstrumentId(Integer instrumentId) {
        instrumentRepository.deleteById(instrumentId);
    }


    @Override
    @Modifying
    public void instrumentShowing(Integer instrumentId) {
        Instrument instrument = instrumentRepository.getOne(instrumentId);
        instrument.setLastShowingDate(LocalDate.now());
        instrumentRepository.save(instrument);
    }

    @Override
    public Instrument getOneById(Integer instrumentId) {
        return instrumentRepository.getOne(instrumentId);
    }

}
