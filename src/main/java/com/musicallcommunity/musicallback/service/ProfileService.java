package com.musicallcommunity.musicallback.service;

import com.musicallcommunity.musicallback.dto.ProfileDto;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.Profile;

import java.util.List;

public interface ProfileService {

    List<Profile> getAll();

    Profile getById(Long id);

    void updateProfile(Profile profile, ProfileDto current) throws ResourceNotFoundException;
}
