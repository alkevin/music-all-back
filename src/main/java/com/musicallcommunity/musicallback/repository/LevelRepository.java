package com.musicallcommunity.musicallback.repository;

import com.musicallcommunity.musicallback.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
}
