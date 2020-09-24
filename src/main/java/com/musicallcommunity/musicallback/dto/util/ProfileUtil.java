package com.musicallcommunity.musicallback.dto.util;

import com.musicallcommunity.musicallback.dto.ProfileDto;

import java.util.ArrayList;
import java.util.List;

public class ProfileUtil {

    public static ProfileDto toProfile(final com.musicallcommunity.musicallback.model.Profile profile) {
        if(profile == null) {
            return null;
        }

        return ProfileDto.ProfileDtoBuilder.builder()
                .id(profile.getId())
                .gender(profile.getGender())
                .age((profile.getAge()))
                .level(LevelUtil.toLevel(profile.getLevel()))
                .instruments(InstrumentUtil.toInstruments(profile.getInstruments()))
                .build();
    }

    public static List<ProfileDto> toProfiles(final List<com.musicallcommunity.musicallback.model.Profile> profiles) {
        final List<ProfileDto> listProfiles = new ArrayList<>();
        if(listProfiles != null) {
            for(final com.musicallcommunity.musicallback.model.Profile profile : profiles) {
                listProfiles.add(toProfile(profile));
            }
        }
        return listProfiles;
    }
}
