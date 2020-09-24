package com.musicallcommunity.musicallback.service;

import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.model.Instrument;
import com.musicallcommunity.musicallback.model.Level;

import java.util.List;

public interface LevelService {

    List<Level> getAll();

    Level getByLevelId(Long levelId);

}
