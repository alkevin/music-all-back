package com.musicallcommunity.musicallback.controller.admin;

import com.musicallcommunity.musicallback.dto.InstrumentDto;
import com.musicallcommunity.musicallback.dto.util.InstrumentUtil;
import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.Instrument;
import com.musicallcommunity.musicallback.payload.ApiResponse;
import com.musicallcommunity.musicallback.payload.InstrumentRequest;
import com.musicallcommunity.musicallback.service.InstrumentService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(value = "/api/admin/instrument")
@Api(description = "instrument admin api")
public class InstrumentAdminController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private InstrumentService instrumentService;

    @Autowired
    public InstrumentAdminController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<InstrumentDto> getInstrumentById(@RequestParam("instrumentId") final Long instrumentId) {
        LOGGER.info("Fetching instrument with id {} : ", instrumentId);

        Instrument instrument = instrumentService.getByInstrumentId(instrumentId);
        InstrumentDto instrumentDto = InstrumentUtil.toInstrument(instrument);

        return new ResponseEntity<>(instrumentDto, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<InstrumentDto> postInstrument(@RequestBody InstrumentRequest instrumentRequest) throws AlreadyExistException, ResourceNotFoundException {
        LOGGER.info("Saving instrument with name : " + instrumentRequest.getName());

        Instrument instrument = instrumentService.createInstrument(instrumentRequest.getName());

        InstrumentDto instrumentDto = InstrumentUtil.toInstrument(instrument);

        return new ResponseEntity<>(instrumentDto,HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<Instrument> delete(@RequestParam("instrumentId") final Long instrumentId) {
        LOGGER.info("Fetching & Deleting Instrument with id {} : ", instrumentId);

        Instrument instrument = instrumentService.getByInstrumentId(instrumentId);

        if (instrument == null) {
            LOGGER.error("Unable to delete. Instrument with id {} not found.", instrumentId);
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Unable to delete. Instrument with id " + instrumentId + " not found.", null);
        }

        instrumentService.delete(instrument.getId());
        return new ApiResponse<>(HttpStatus.OK.value(), "Instrument deleted successfully.", null);
    }
}
