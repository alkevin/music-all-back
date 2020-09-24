package com.musicallcommunity.musicallback.service;

import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.Conversation;
import com.musicallcommunity.musicallback.model.Instrument;
import com.musicallcommunity.musicallback.model.Message;
import com.musicallcommunity.musicallback.model.User;

import java.util.List;
import java.util.Set;

public interface InstrumentService {

    Set<Instrument> getAll();

    Instrument getByInstrumentId(Long instrumentId);

    Instrument save(Instrument instrument) throws AlreadyExistException;

    Instrument createInstrument(String instrument) throws AlreadyExistException;

    void delete(Long instrumentId);
}
