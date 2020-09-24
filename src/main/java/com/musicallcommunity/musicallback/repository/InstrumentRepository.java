package com.musicallcommunity.musicallback.repository;

import com.musicallcommunity.musicallback.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
}
