package com.musicallcommunity.musicallback.service.impl;

import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.Instrument;
import com.musicallcommunity.musicallback.model.Level;
import com.musicallcommunity.musicallback.model.LevelName;
import com.musicallcommunity.musicallback.repository.InstrumentRepository;
import com.musicallcommunity.musicallback.repository.LevelRepository;
import com.musicallcommunity.musicallback.service.LevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "levelService")
public class LevelServiceImpl implements LevelService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public List<Level> getAll() {
        return levelRepository.findAll();
    }

    @Override
    public Level getByLevelId(Long levelId) {
        return levelRepository.findById(levelId).orElseThrow(() -> new ResourceNotFoundException("Level", "id", levelId));
    }

}
