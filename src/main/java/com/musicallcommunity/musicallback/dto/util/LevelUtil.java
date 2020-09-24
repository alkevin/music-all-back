package com.musicallcommunity.musicallback.dto.util;

import com.musicallcommunity.musicallback.dto.LevelDto;
import com.musicallcommunity.musicallback.model.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelUtil {
    public static LevelDto toLevel(final com.musicallcommunity.musicallback.model.Level level) {
        if(level == null) {
            return null;
        }

        return LevelDto.LevelDtoBuilder.builder()
                .id(level.getId())
                .name(level.getName().name())
                .build();
    }

    public static List<LevelDto> toLevels(final List<Level> levels) {
        final List<LevelDto> listLevels = new ArrayList<>();
        if(levels != null) {
            for(final com.musicallcommunity.musicallback.model.Level level : levels) {
                listLevels.add(toLevel(level));
            }
        }
        return listLevels;
    }
}
