package com.musicallcommunity.musicallback.service.impl;

import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.*;
import com.musicallcommunity.musicallback.repository.InstrumentRepository;
import com.musicallcommunity.musicallback.service.InstrumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service(value = "instrumentService")
public class InstrumentServiceImpl implements InstrumentService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private InstrumentRepository instrumentRepository;

    @Autowired
    public InstrumentServiceImpl(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public Set<Instrument> getAll() {
        List<Instrument> listInstruments = instrumentRepository.findAll();
        Set<Instrument> setInstruments = new LinkedHashSet<>(listInstruments);
        return setInstruments;
    }

    @Override
    public Instrument getByInstrumentId(Long instrumentId) {
        return instrumentRepository.findById(instrumentId).orElseThrow(() -> new ResourceNotFoundException("Instrument", "id", instrumentId));
    }

    @Override
    public Instrument createInstrument(String name) {
        LOGGER.info("Creating instrument with name {} ", name);

        Instrument instrument = new Instrument();
        instrument.setName(name);

        return save(instrument);
    }

    @Override
    public Instrument save(Instrument instrument){
        LOGGER.info("Saving instrument : " + instrument.toString());
        return instrumentRepository.save(instrument);
    }

    @Override
    public void delete(Long instrumentId) {
        instrumentRepository.deleteById(instrumentId);
    }

}
