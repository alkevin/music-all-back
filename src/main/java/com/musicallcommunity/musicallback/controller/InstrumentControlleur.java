package com.musicallcommunity.musicallback.controller;

import com.musicallcommunity.musicallback.controller.admin.UserController;
import com.musicallcommunity.musicallback.dto.InstrumentDto;
import com.musicallcommunity.musicallback.dto.util.InstrumentUtil;
import com.musicallcommunity.musicallback.model.Instrument;
import com.musicallcommunity.musicallback.service.InstrumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@Validated
@RequestMapping(value = "/api/user/instrument")
public class InstrumentControlleur {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private InstrumentService instrumentService;

    @Autowired
    public InstrumentControlleur(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<InstrumentDto>> getInstruments() {
        LOGGER.info("Fetching all instruments");

        Set<Instrument> instruments = instrumentService.getAll();
        List<InstrumentDto> instrumentsDto = InstrumentUtil.toInstruments(instruments);

        return new ResponseEntity<>(instrumentsDto, HttpStatus.OK);
    }

}
