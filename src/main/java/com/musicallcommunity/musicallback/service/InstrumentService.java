package com.musicallcommunity.musicallback.service;

import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.model.Instrument;

import java.util.Set;

public interface InstrumentService {

    Set<Instrument> getAll();

    Instrument getByInstrumentId(Long instrumentId);

    Instrument save(Instrument instrument) throws AlreadyExistException;

    Instrument createInstrument(String instrument) throws AlreadyExistException;

    void delete(Long instrumentId);
}
