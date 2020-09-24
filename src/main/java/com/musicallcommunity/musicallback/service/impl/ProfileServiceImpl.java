package com.musicallcommunity.musicallback.service.impl;

import com.musicallcommunity.musicallback.dto.InstrumentDto;
import com.musicallcommunity.musicallback.dto.ProfileDto;
import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.*;
import com.musicallcommunity.musicallback.repository.InstrumentRepository;
import com.musicallcommunity.musicallback.repository.ProfileRepository;
import com.musicallcommunity.musicallback.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service(value = "profileService")
public class ProfileServiceImpl implements ProfileService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private ProfileRepository profileRepository;
    private InstrumentRepository instrumentRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, InstrumentRepository instrumentRepository) {
        this.profileRepository = profileRepository;
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getById(Long id) {
        return profileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Profile", "id", id));
    }

    @Override
    public void updateProfile(Profile profile, ProfileDto current) {
        profile.setGender(current.getGender());
        profile.setAge(current.getAge());
        if(current.getLevel() != null) {
            profile.setLevelId(current.getLevel().getId());
        }
        if(current.getInstruments() != null) {
            Set<Instrument> listInstru = new LinkedHashSet<>();
            for (InstrumentDto instrument : current.getInstruments()) {
                Instrument instru = instrumentRepository.findById(instrument.getId()).orElseThrow(() -> new ResourceNotFoundException("Instrument", "id", instrument.getId()));
                listInstru.add(instru);
            }
            profile.setInstruments(listInstru);
        }
        profileRepository.save(profile);
    }

    
}
