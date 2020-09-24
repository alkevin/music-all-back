package com.musicallcommunity.musicallback.controller.admin;

import com.musicallcommunity.musicallback.dto.LevelDto;
import com.musicallcommunity.musicallback.dto.util.LevelUtil;
import com.musicallcommunity.musicallback.model.Level;
import com.musicallcommunity.musicallback.service.LevelService;
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
@RequestMapping(value = "/api/admin/level")
@Api(description = "level admin api")
public class LevelAdminController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private LevelService levelService;

    @Autowired
    public LevelAdminController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<LevelDto> getLevelById(@RequestParam("levelId") final Long levelId) {
        LOGGER.info("Fetching level with id {} : ", levelId);

        Level level = levelService.getByLevelId(levelId);
        LevelDto levelDto = LevelUtil.toLevel(level);

        return new ResponseEntity<>(levelDto, HttpStatus.OK);
    }
}
