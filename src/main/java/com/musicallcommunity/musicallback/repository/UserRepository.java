package com.musicallcommunity.musicallback.repository;

import com.musicallcommunity.musicallback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByMail(String mail);

    User findByIdAndMail(Long id, String mail);

    Boolean existsByMail(String mail);
}
