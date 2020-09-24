package com.musicallcommunity.musicallback.service;

import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ForbiddenException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.User;
import com.musicallcommunity.musicallback.payload.SignUpRequest;

import java.util.List;

public interface UserService {

    User getById(Long id);

    User getByMail(String mail) throws ResourceNotFoundException;

    User getByToken(String token) throws ResourceNotFoundException;

    List<User> getAll();

    User save(User user) throws AlreadyExistException;

    User createUser(SignUpRequest signup) throws AlreadyExistException;

    void delete(Long id);

    User updateUser(Long id, UserDto current) throws ResourceNotFoundException;

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(Long id, String token) throws ForbiddenException;

    void changeUserPassword(User user, String password) throws ResourceNotFoundException;

    boolean checkIfValidOldPassword(User user, String oldPassword);

    boolean isUserExist(String mail) throws ResourceNotFoundException;

    boolean isAdmin(Long id,String mail) throws ResourceNotFoundException;

    UserDto fetchUserAfterAuth(String mail) throws ResourceNotFoundException;

    User connectUser(User user) throws ResourceNotFoundException;

    User disconnectUser(User user) throws ResourceNotFoundException;

}
