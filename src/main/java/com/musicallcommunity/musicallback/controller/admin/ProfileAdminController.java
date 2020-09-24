package com.musicallcommunity.musicallback.controller.admin;

import com.musicallcommunity.musicallback.dto.ProfileDto;
import com.musicallcommunity.musicallback.dto.util.ProfileUtil;
import com.musicallcommunity.musicallback.model.Profile;
import com.musicallcommunity.musicallback.service.ProfileService;
import io.swagger.annotations.Api;
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
@RequestMapping(value = "/api/admin/profile")
@Api(description = "authentication api")
public class ProfileAdminController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private ProfileService profileService;

    @Autowired
    public ProfileAdminController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<ProfileDto>> getInstruments() {
        LOGGER.info("Fetching all profiles");

        List<Profile> profiles = profileService.getAll();
        List<ProfileDto> profilesDto = ProfileUtil.toProfiles(profiles);

        return new ResponseEntity<>(profilesDto, HttpStatus.OK);
    }
}
