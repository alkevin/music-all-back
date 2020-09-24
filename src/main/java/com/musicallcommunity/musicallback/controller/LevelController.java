package com.musicallcommunity.musicallback.controller;

import com.musicallcommunity.musicallback.controller.admin.UserController;
import com.musicallcommunity.musicallback.dto.InstrumentDto;
import com.musicallcommunity.musicallback.dto.LevelDto;
import com.musicallcommunity.musicallback.dto.util.InstrumentUtil;
import com.musicallcommunity.musicallback.dto.util.LevelUtil;
import com.musicallcommunity.musicallback.model.Instrument;
import com.musicallcommunity.musicallback.model.Level;
import com.musicallcommunity.musicallback.service.InstrumentService;
import com.musicallcommunity.musicallback.service.LevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/api/user/level")
public class LevelController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<LevelDto>> getLevels() {
        LOGGER.info("Fetching all levels");

        List<Level> levels = levelService.getAll();
        List<LevelDto> levelsDto = LevelUtil.toLevels(levels);

        return new ResponseEntity<>(levelsDto, HttpStatus.OK);
    }
}
