package com.musicallcommunity.musicallback.service.impl;

import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.exception.ForbiddenException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.PasswordResetToken;
import com.musicallcommunity.musicallback.model.Role;
import com.musicallcommunity.musicallback.model.RoleName;
import com.musicallcommunity.musicallback.model.User;
import com.musicallcommunity.musicallback.repository.PasswordResetTokenRepository;
import com.musicallcommunity.musicallback.repository.RoleRepository;
import com.musicallcommunity.musicallback.repository.UserRepository;
import com.musicallcommunity.musicallback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordResetTokenRepository passwordTokenRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository
            , PasswordResetTokenRepository passwordTokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordTokenRepository = passwordTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByMail(String mail) throws ResourceNotFoundException {
        if (userRepository.findByMail(mail).equals(null)){
            throw new ResourceNotFoundException("User", "mail", mail);
        }
        return userRepository.findByMail(mail);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User getByToken(String token) throws ResourceNotFoundException {
        PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
        if(passToken == null) {
            throw new ResourceNotFoundException("User", "token", passToken.getUser());
        }
        return passToken.getUser();
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User save(User user){
        LOGGER.info("Saving user : " + user.toString());
        return userRepository.save(user);
    }

    /**
     * Update user information by admin
     * @param userToUpdate
     * @param current
     * @return
     * @throws ResourceNotFoundException
     */
    @Override
    public User updateUser(User userToUpdate, UserDto current) throws ResourceNotFoundException {
        /**
         * We need to insert a business code here
         */
        if (getById(userToUpdate.getId()) == null){
            throw new ResourceNotFoundException("User", "id", userToUpdate.getId());
        }
        userToUpdate.setFirstName(current.getFirstName());
        userToUpdate.setLastName(current.getLastName());
        userToUpdate.setModificationDate(new Date());
        return userRepository.save(userToUpdate);
    }

    /**
     * Update the user profile
     * @param id
     * @param current
     * @return
     * @throws ResourceNotFoundException
     */
    @Override
    public User updateProfile(Long id, UserDto current) throws ResourceNotFoundException {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        existingUser.setFirstName(current.getFirstName());
        existingUser.setLastName(current.getLastName());
        existingUser.setModificationDate(new Date());
        return userRepository.save(existingUser);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public String validatePasswordResetToken(Long id, String token) throws ForbiddenException {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
        if((passToken == null) || (passToken.getUser().getId() != id)) {
            throw new ForbiddenException("User", "cause", "invalidToken");
        }

        final Calendar cal = Calendar.getInstance();
        if((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0 ) {
            throw new ForbiddenException("User", "cause", "tokenExpired");
        }

        /*final User user = passToken.getUser();
        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
        SecurityContextHolder.getContext().setAuthentication(auth);*/
        return "Authorize";
    }

    @Override
    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public boolean isUserExist(String mail) throws ResourceNotFoundException {
        return getByMail(mail) != null;
    }

    @Override
    public boolean isAdmin(Long id, String mail) throws ResourceNotFoundException {
        User user = userRepository.findByIdAndMail(id,mail);
        Role userRole = roleRepository.findByName(RoleName.ADMIN_ROLE).
                orElseThrow(() -> new ResourceNotFoundException("Role", "RoleName", RoleName.ADMIN_ROLE));
        boolean isAdmin = false;
        if(user.getRoles().contains(userRole)) {
            isAdmin = true;
        }
        return isAdmin;
    }
}
