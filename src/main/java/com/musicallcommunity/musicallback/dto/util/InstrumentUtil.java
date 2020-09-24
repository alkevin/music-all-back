package com.musicallcommunity.musicallback.dto.util;

import com.musicallcommunity.musicallback.dto.InstrumentDto;
import com.musicallcommunity.musicallback.model.Instrument;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InstrumentUtil {
    public static InstrumentDto toInstrument(final com.musicallcommunity.musicallback.model.Instrument instrument) {
        if(instrument == null) {
            return null;
        }

        return InstrumentDto.InstrumentDtoBuilder.builder()
                .id(instrument.getId())
                .name(instrument.getName())
                .build();
    }

    public static List<InstrumentDto> toInstruments(final Set<Instrument> instruments) {
        final List<InstrumentDto> listInstruments = new ArrayList<>();
        if(instruments != null) {
            for(final com.musicallcommunity.musicallback.model.Instrument instrument : instruments) {
                listInstruments.add(toInstrument(instrument));
            }
        }
        return listInstruments;
    }
}
